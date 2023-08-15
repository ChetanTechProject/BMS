package com.example.moviebooking.service;

import java.util.List;

import com.example.moviebooking.data.dto.BookSeatsDto;
import com.example.moviebooking.domain.MovieScreening;

public interface MovieScreeningService {
	List<MovieScreening> getAllMovieDetails(String movieName, String city, String screeningDt);

	public int bookSeats(BookSeatsDto bookSeatsDto,int seats, long orderId) throws Exception;

	public int getBookedSeats(BookSeatsDto bookSeatsDto);

	public int getTotalSeats(BookSeatsDto bookSeatsDto);

	public long generateOrder(Long userId, int numSeats);

	public String submitPaymentDetail(Long userId,String transactionAmount,Long orderId);

	public void updateOrderStatus(Long orderId,String Orderstatus,String PaymentStatus);
}
