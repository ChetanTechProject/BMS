package com.example.moviebooking.dao;

import com.example.moviebooking.data.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepo extends  JpaRepository <Order, Long> {
	 

}