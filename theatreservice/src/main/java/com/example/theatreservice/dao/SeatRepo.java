package com.example.theatreservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.theatreservice.data.entity.Seat;



@Repository
public interface SeatRepo extends  JpaRepository <Seat, Integer> { 
	 
	

}