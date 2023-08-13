package com.example.theatreservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.theatreservice.data.entity.Screen;



@Repository
public interface ScreenRepo extends  JpaRepository <Screen, Long> { 
	public Screen findByScreenId(long screenId);
	public List<Screen> findByTheatreId(long theatreId);
}
