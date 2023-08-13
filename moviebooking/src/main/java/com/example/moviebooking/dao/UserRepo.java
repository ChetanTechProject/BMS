package com.example.moviebooking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.moviebooking.data.entity.User;



@Repository
public interface UserRepo extends  JpaRepository <User, Integer> { 
	 
	

}
