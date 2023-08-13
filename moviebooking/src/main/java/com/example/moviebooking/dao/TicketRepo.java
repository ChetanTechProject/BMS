package com.example.moviebooking.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.moviebooking.data.entity.Ticket;



@Repository
public interface TicketRepo extends  JpaRepository <Ticket, Integer> { 
	 
	List<Ticket> findByScreeningId(long screeningId);

}