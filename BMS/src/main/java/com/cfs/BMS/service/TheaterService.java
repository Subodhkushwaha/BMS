package com.cfs.BMS.service;

import com.cfs.BMS.dto.TheaterRequest;
import com.cfs.BMS.entity.City;
import com.cfs.BMS.entity.Theater;
import com.cfs.BMS.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterService {

    private final TheaterRepository theaterRepository;
    private final CityService cityService;

    // Add Theater
    public Theater addTheater(TheaterRequest request) {

        City city = cityService.getCityById(request.getCityId());

        Theater theater = Theater.builder()
                .name(request.getName())
                .address(request.getAddress())
                .city(city)
                .build();

        return theaterRepository.save(theater);
    }

    // Get All Theaters
    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    // Get Theater By Id
    public Theater getTheaterById(Long id) {
        return theaterRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Theater not found with id: " + id));
    }

    // Get Theater By City
    public List<Theater> getTheaterByCity(Long cityId) {
        return theaterRepository.findByCityId(cityId);
    }

    // Update Theater
    public Theater updateTheater(Long id, TheaterRequest request) {

        Theater theater = getTheaterById(id);
        City city = cityService.getCityById(request.getCityId());

        theater.setName(request.getName());
        theater.setAddress(request.getAddress());
        theater.setCity(city);

        return theaterRepository.save(theater);
    }

    // Delete Theater
    public void deleteTheater(Long id) {
        theaterRepository.deleteById(id);
    }
}