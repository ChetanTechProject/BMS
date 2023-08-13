package com.example.theatreservice.controller;

import java.util.List;

import com.example.theatreservice.data.dto.TheatreDto;
import com.example.theatreservice.service.TheatreManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/movie/theatre")
@RestController
public class TheatreController {

	@Autowired
	private TheatreManageService theatreManageService;

	Logger log = LoggerFactory.getLogger(TheatreController.class);

	@CrossOrigin
	@PostMapping("/onBoard")
	public String bookSeats(@RequestBody TheatreDto theatreDto) {

		try {
			theatreManageService.onBoardTheatre(theatreDto);
		} catch (Exception e) {
			log.error("Error Saving the Transaction, Please try later! ");
			return "Sorry Unable to Onboard Theatre";
		}

		return theatreDto.getTheatreName() + "  Succesfully onBoarded!";
	}

}
