package com.example.moviebooking.controller;

import java.util.List;

import ch.qos.logback.core.CoreConstants;
import com.example.moviebooking.util.BMSConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.moviebooking.data.dto.BookSeatsDto;
import com.example.moviebooking.data.dto.MovieDetailsDto;
import com.example.moviebooking.domain.MovieScreening;
import com.example.moviebooking.service.MovieScreeningService;

@RequestMapping("/movie/screening")
@RestController
public class MovieScreeningController {

	@Autowired
	private MovieScreeningService movieScreeningService;

	Logger log = LoggerFactory.getLogger(MovieScreeningController.class);

	@CrossOrigin
	@GetMapping("/details")
	public List<MovieScreening> getUiDetails(@RequestBody MovieDetailsDto movieDetailsDto) {
		log.info("Screening details endPoint called");

		if (movieDetailsDto != null) {
			return movieScreeningService.getAllMovieDetails(movieDetailsDto.getMovieName(),
					movieDetailsDto.getMovieCity(), movieDetailsDto.getScreeningDate());
		}

		return null;
	}

	@CrossOrigin
	@PostMapping("/bookseats") 
	public String bookSeats(@RequestBody BookSeatsDto bookSeatsDto) {

		int bookedSeats = this.movieScreeningService.getBookedSeats(bookSeatsDto);
		log.info("Booked Seats " + bookedSeats);
		int totalSeats = this.movieScreeningService.getTotalSeats(bookSeatsDto);
		log.info("Total Seats " + totalSeats);

		if ((bookedSeats + bookSeatsDto.getNumSeats()) > totalSeats)
			return "Sorry " +bookSeatsDto.getNumSeats()+  " Seats Not Avaliable";

		try {
			//generate the order id
			long userId = bookSeatsDto.getUserDetailsDTO().getUserId();
			long orderId = this.movieScreeningService.generateOrder(userId,bookSeatsDto.getNumSeats());
			System.out.println("Order ID ********** " + orderId);
			//Initiate Payment
            String paymentResult =  this.movieScreeningService.submitPaymentDetail(userId,bookSeatsDto.getUserDetailsDTO().getPaymentDto().getTransactionAmount(),orderId);
			 if(BMSConstants.PAYMENT_SUCCESS.equalsIgnoreCase(paymentResult)) {
				 //append the number of seat's with booked seats to update new booked seats
				 this.movieScreeningService.bookSeats(bookSeatsDto, bookedSeats + bookSeatsDto.getNumSeats(), orderId);
				 //update order status as completed and payment as Success
				 this.movieScreeningService.updateOrderStatus(orderId,BMSConstants.ORDER_COMPLETED,BMSConstants.PAYMENT_SUCCESS);
			 }else{
				 log.error("Error during Payment for Order " + orderId);
				 //update order status as Failed and payment as Failed
				 this.movieScreeningService.updateOrderStatus(orderId,BMSConstants.ORDER_FAILED,BMSConstants.PAYMENT_FAILED);
				 return "Sorry Payment Service is Down, Try after Sometime";
			 }
		} catch (Exception e) {
           log.error("Error Saving the Transaction, Please try later! ");
           return "Sorry Unable to Book Seats Currently";
		}

		return bookSeatsDto.getNumSeats() + " Tickets Succesfully booked for Movie " + bookSeatsDto.getMovieName() + "!";
	}

}
