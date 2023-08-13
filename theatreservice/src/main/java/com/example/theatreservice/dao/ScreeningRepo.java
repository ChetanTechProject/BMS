package com.example.theatreservice.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.theatreservice.data.entity.Screening;



@Repository
public interface ScreeningRepo extends  JpaRepository <Screening, Long> { 
	
	 List<Screening> findByMovieNameAndTheatreIdAndScreeningDate(String movieName, long theatreId, Date screeningDate);
	 Screening findByMovieNameAndTheatreIdAndScreeningDateAndScreeningTime(String movieName, long theatreId, Date screeningDate, Time screeningTime);
}
