package com.example.theatreservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.theatreservice.data.entity.Ticket;



@Repository
public interface TicketRepo extends  JpaRepository <Ticket, Integer> { 
	 
	List<Ticket> findByScreeningId(long screeningId);

}