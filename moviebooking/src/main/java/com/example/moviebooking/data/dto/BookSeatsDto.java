package com.example.moviebooking.data.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookSeatsDto {

	
    private String movieName;
    private int screenId;
    private String theatreName;
    private String theatreCity;
    private String screeningDate;
    private String screeningTime;
    private int numSeats;
    private List<String> preferredSeats;

    //user details
    private UserDetailsDTO userDetailsDTO;

	
}
