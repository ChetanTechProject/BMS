package com.example.theatreservice.data.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheatreDto {

	
private String theatreName;
private String theatreCity;
private int numberOfScreen;
private List<ScreenDTO> screenDTO;

}
