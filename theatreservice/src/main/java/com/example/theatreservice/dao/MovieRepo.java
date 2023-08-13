package com.example.theatreservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.theatreservice.data.entity.Movie;



@Repository
public interface MovieRepo extends  JpaRepository <Movie, String> { 
    Movie findByMovieName(String movieName);
    Movie findByMovieId(long movieId);
}
