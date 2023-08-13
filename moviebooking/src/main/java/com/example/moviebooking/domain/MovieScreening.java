package com.example.moviebooking.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieScreening {
    private String movieName;
    private int screenId;
    private String theatreName;
    private String theatreCity;
    private String screeningDate;
    private String screeningTime;
    private int bookedSeats;

}
