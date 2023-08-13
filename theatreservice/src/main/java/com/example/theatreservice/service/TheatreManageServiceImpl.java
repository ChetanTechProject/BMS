package com.example.theatreservice.service;



import com.example.theatreservice.dao.ScreenRepo;
import com.example.theatreservice.dao.TheatreRepo;
import com.example.theatreservice.data.dto.ScreenDTO;
import com.example.theatreservice.data.dto.TheatreDto;
import com.example.theatreservice.data.entity.Screen;
import com.example.theatreservice.data.entity.Theatre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


@Service
public class TheatreManageServiceImpl implements TheatreManageService {

	Logger log = LoggerFactory.getLogger(TheatreManageServiceImpl.class);

	@PersistenceContext
	private EntityManager entityMgr;

	@Autowired
	private TheatreRepo theatreRepository;
	@Autowired
	private ScreenRepo screenRepo;

	@Override
	public void onBoardTheatre(TheatreDto theatreDto) {

		//Save Theatre Details
		Theatre theatre = new Theatre();
		theatre.setTheatreName(theatreDto.getTheatreName());
		theatre.setTheatreCity(theatreDto.getTheatreCity());

		theatre = theatreRepository.save(theatre);

		//Save Screen Details
		List<ScreenDTO> screenDTOList = theatreDto.getScreenDTO();
		Screen screen = null;
		List<Screen> screenList = new ArrayList<>();
		for(ScreenDTO screenDTO : screenDTOList) {

			screen = new Screen();
			screen.setTheatreId(theatre.getTheatreId());
			screen.setScreenId(screenDTO.getScreenNumber());
			screen.setSeatsNum(screenDTO.getNumberOfSeats());
			screenList.add(screen);
		}
		if(screenList != null && screenList.size() > 0){
			screenRepo.saveAll(screenList);
		}

	}
}
