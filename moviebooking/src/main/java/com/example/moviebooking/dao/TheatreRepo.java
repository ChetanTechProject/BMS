package com.example.moviebooking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.moviebooking.data.entity.Theatre;



@Repository
public interface TheatreRepo extends  JpaRepository <Theatre, Integer> { 
    Theatre findByTheatreId(Long theatreId);
    Theatre findByTheatreNameAndTheatreCity(String theatreName, String theatreCity);
}
