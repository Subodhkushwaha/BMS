package com.cfs.BMS.service;


import com.cfs.BMS.dto.ShowRequest;
import com.cfs.BMS.entity.Movie;
import com.cfs.BMS.entity.Screen;
import com.cfs.BMS.entity.Show;
import com.cfs.BMS.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowService {

    private final ShowRepository showRepository;
    private final MovieService movieService;
    private final ScreenService screenService;

    //addshow
    public Show addShow(ShowRequest request)
    {
        Movie movie =movieService.getMovieById(request.getMovieId());
        Screen screen=screenService.getScreenById(request.getScreenId());
        Show show=Show.builder()
                .movie(movie)
                .screen(screen)
                .showDate(request.getShowDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .ticketPrice(request.getTicketPrice())
                .build();

        return showRepository.save(show);
    }

    public List<Show> getAllShow()
    {
        return showRepository.findAll();
    }
    public Show getShowById(Long id)
    {
        return showRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Show not found with id: "+id));

    }

    public List<Show> getShowByMovie(Long movieId)
    {
        return showRepository.findByMovieId(movieId);
    }

    public List<Show> getShowByMovieAndDate(Long movieId, LocalDate date)
    {
        return showRepository.findByMovieIdAndShowDate(movieId,date);
    }

    public Show updateShow(Long id, ShowRequest request) {

        Show show = getShowById(id);

        Movie movie = movieService.getMovieById(request.getMovieId());
        Screen screen = screenService.getScreenById(request.getScreenId());

        show.setMovie(movie);
        show.setScreen(screen);
        show.setShowDate(request.getShowDate());
        show.setStartTime(request.getStartTime());
        show.setEndTime(request.getEndTime());
        show.setTicketPrice(request.getTicketPrice());

        return showRepository.save(show);
    }

    public void deleteShow(Long id) {
        showRepository.deleteById(id);
    }
}