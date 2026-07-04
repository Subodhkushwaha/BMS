package com.cfs.BMS.service;


import com.cfs.BMS.dto.ScreenRequest;
import com.cfs.BMS.entity.Screen;
import com.cfs.BMS.entity.Theater;
import com.cfs.BMS.repository.ScreenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreenService {

    private final ScreenRepository screenRepository;
    private final TheaterService theaterService;

    //addscreen

    public List<Screen> getAllScreen()
    {
        return screenRepository.findAll();
    }

    public Screen getScreenById(Long id)
    {
        return screenRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Screen not found with id: "+id));

    }

    public List<Screen> getScreenByTheater(Long theaterId)
    {
        return screenRepository.findByTheaterId(theaterId);
    }

    public void deleteScreen(Long id) {

        Screen screen = getScreenById(id);

        screenRepository.delete(screen);
    }

    public Screen addScreen(ScreenRequest request) {

        Theater theater = theaterService.getTheaterById(request.getTheaterId());

        Screen screen = Screen.builder()
                .name(request.getName())
                .totalSeats(request.getTotalSeats())
                .theater(theater)
                .build();

        return screenRepository.save(screen);
    }

    public Screen updateScreen(Long id, ScreenRequest request) {

        Screen screen = getScreenById(id);
        Theater theater = theaterService.getTheaterById(request.getTheaterId());

        screen.setName(request.getName());
        screen.setTotalSeats(request.getTotalSeats());
        screen.setTheater(theater);

        return screenRepository.save(screen);
    }
}