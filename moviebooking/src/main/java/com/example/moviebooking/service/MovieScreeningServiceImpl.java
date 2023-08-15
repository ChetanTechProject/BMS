package com.example.moviebooking.service;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ch.qos.logback.core.CoreConstants;
import com.example.moviebooking.dao.*;
import com.example.moviebooking.data.entity.Order;
import com.example.moviebooking.util.BMSConstants;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.moviebooking.data.dto.BookSeatsDto;
import com.example.moviebooking.data.entity.Screening;
import com.example.moviebooking.data.entity.Seat;
import com.example.moviebooking.data.entity.Theatre;
import com.example.moviebooking.domain.MovieScreening;
import com.example.moviebooking.util.DateUtil;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieScreeningServiceImpl implements MovieScreeningService {

	Logger log = LoggerFactory.getLogger(MovieScreeningServiceImpl.class);

	@Value("${moviescreeningdetails}")
	private String getmoviescreeningdetails;

	@PersistenceContext
	private EntityManager entityMgr;

	@Autowired
	private ScreeningRepo screeningRepository;

	@Autowired
	private ScreenRepo screenRepository;

	@Autowired
	private TheatreRepo theatreRepository;
	
	@Autowired
	private SeatRepo seatRepository;

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${payment.gaetway}")
	private String url;

	private final String PAYMENT_SERVICE = "paymentService";
	@Override
	public List<MovieScreening> getAllMovieDetails(String movieName, String city, String screeningDt) {

		Query q = entityMgr.createNativeQuery(getmoviescreeningdetails);
		q.setParameter(1, movieName);
		q.setParameter(2, city);
		q.setParameter(3, screeningDt);

		List resultList = q.getResultList();

		MovieScreening movieScreening = null;
		List<MovieScreening> movieList = new ArrayList<MovieScreening>();
		BigInteger ingr = null;
		BigInteger availbleSeats = null;

		for (Object obj : resultList) {
			movieScreening = new MovieScreening();
			Object[] objArray = (Object[]) obj;
			movieScreening.setTheatreName((String) objArray[0]);
			movieScreening.setTheatreCity(city);
			ingr = (BigInteger) objArray[1];
			movieScreening.setScreenId(ingr.intValue());
			movieScreening.setMovieName(movieName);
			movieScreening.setScreeningDate(screeningDt);
			movieScreening.setScreeningTime(DateUtil.convertTimeToString((java.sql.Time) objArray[2]));
			availbleSeats = (BigInteger) objArray[3];
			movieScreening.setAvailableSeats(availbleSeats.intValue());
			movieList.add(movieScreening);

		}

		return movieList;
	}

	@Override
	@Transactional
	public int bookSeats(BookSeatsDto bookSeatsDto, int seats, long orderId) throws Exception{
		final String COLON_DELIMETER = ":";
		List<Seat> preferredSeatList = new ArrayList<Seat>();
		Seat prefSeatEntity = null;
		char rowId ;
		int seatNbr = -1;

		Screening screening = getScreening(bookSeatsDto);
		screening.setBookedTickets(seats);
		//save screening
		screeningRepository.save(screening);
		log.info("Screening Info Saved ");
		//save preferred seats
		List<String> preferredSeats = bookSeatsDto.getPreferredSeats();
		long screenId = screening.getScreenId();
		for(String prefSeat : preferredSeats) {
			String[]seatArray = prefSeat.split(COLON_DELIMETER);
			if(seatArray != null && seatArray.length > 0) {
				prefSeatEntity = new Seat();
				rowId = seatArray[0].charAt(0);
				seatNbr = Integer.valueOf(seatArray[1]);
				log.info("Saving Row id " + rowId  + " seat number " + seatNbr);
				prefSeatEntity.setRowId(rowId);
				prefSeatEntity.setSeatNbr(seatNbr);
				prefSeatEntity.setScreenId(screenId);
				prefSeatEntity.setTheatreId(screening.getTheatreId());
				prefSeatEntity.setOrderId(orderId);
				preferredSeatList.add(prefSeatEntity);
				}
		}
		
		seatRepository.saveAll(preferredSeatList);
		log.info("Preferred Seat Details saved.  ");
		return getBookedSeats(bookSeatsDto);
	}

	@Override
	public int getBookedSeats(BookSeatsDto bookSeatsDto) {
		Screening screening = getScreening(bookSeatsDto);
		return screening.getBookedTickets();
	}

	@Override
	public int getTotalSeats(BookSeatsDto bookSeatsDto) {
		return screenRepository.findByScreenId(bookSeatsDto.getScreenId()).getSeatsNum();
	}

	@Override
	public long generateOrder(Long userId, int numSeats) {
		//generate the Order if for the Transaction
		Order order = new Order();
		order.setUserId(userId);
		order.setOrderStatus(BMSConstants.ORDER_INPROGRESS);
		order.setPaymentStatus(BMSConstants.PAYMENT_NOT_INITIATED);
		order.setNumOfSeats(numSeats);
		order = orderRepo.save(order);
		return order.getOrderId();
	}

	@Override
	@CircuitBreaker(name=PAYMENT_SERVICE,fallbackMethod = "paymentGatewayFallBack")
	public String submitPaymentDetail(Long userId, String transactionAmount, Long orderId) {
		return restTemplate.getForObject(url, String.class);
	}

	public String paymentGatewayFallBack(Exception e){
		System.out.println("Fall Back Method called");
		return "FAILED";
	}

	@Override
	public void updateSuccessOrder(Long orderId,String Orderstatus,String PaymentStatus) {
		if(orderId != null){
			Order order = orderRepo.findById(orderId).get();
			order.setOrderStatus(BMSConstants.ORDER_COMPLETED);
			order.setPaymentStatus(BMSConstants.PAYMENT_SUCCESS);
			orderRepo.save(order);
		}
	}

	private Screening getScreening(BookSeatsDto bookSeatsDto) {
		Theatre theatre = theatreRepository.findByTheatreNameAndTheatreCity(bookSeatsDto.getTheatreName(),
				bookSeatsDto.getTheatreCity());
		if (theatre == null)
			return null;

		java.sql.Time time = null;
		log.info("Converted Date " + java.sql.Date.valueOf(bookSeatsDto.getScreeningDate()));
		try {
			time = DateUtil.convertStringToTime(bookSeatsDto.getScreeningTime());
			log.info("Converted Time " + time);
		} catch (ParseException pe) {
			log.error(pe.getMessage());
			return null;
		}

		return screeningRepository.findByMovieNameAndTheatreIdAndScreeningDateAndScreeningTime(
				bookSeatsDto.getMovieName(), theatre.getTheatreId(),
				java.sql.Date.valueOf(bookSeatsDto.getScreeningDate()), time);
	}

}
