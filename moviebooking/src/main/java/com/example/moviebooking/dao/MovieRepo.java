package com.example.moviebooking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.moviebooking.data.entity.Movie;



@Repository
public interface MovieRepo extends  JpaRepository <Movie, String> { 
    Movie findByMovieName(String movieName);
    Movie findByMovieId(long movieId);
}
