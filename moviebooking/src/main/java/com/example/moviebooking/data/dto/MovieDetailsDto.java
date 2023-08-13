package com.example.moviebooking.data.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDetailsDto {

	
	private String movieName;
	private String movieCity;
	private String screeningDate;
	
}
