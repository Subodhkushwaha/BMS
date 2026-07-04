package com.cfs.BMS.service;


import com.cfs.BMS.dto.DashboardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class AdminService {

    private final com.cfs.BMS.repository.MovieRepository movieRepository;
    private final com.cfs.BMS.repository.TheaterRepository theaterRepository;
    private final com.cfs.BMS.repository.ShowRepository showRepository;
    private final com.cfs.BMS.repository.BookingRepository bookingRepository;
    private final com.cfs.BMS.repository.UserRepository userRepository;

    public DashboardResponse getDashboard(){

        double revenue = bookingRepository.findAll()
                .stream()
                .mapToDouble(com.cfs.BMS.entity.Booking::getTotalPrice)
                .sum();

        return DashboardResponse.builder()
                .totalMovies(movieRepository.count())
                .totalTheaters(theaterRepository.count())
                .totalShows(showRepository.count())
                .totalBookings(bookingRepository.count())
                .totalUsers(userRepository.count())
                .totalRevenue(revenue)
                .build();
    }

    // Movie
    public com.cfs.BMS.entity.Movie addMovie(com.cfs.BMS.entity.Movie movie){
        return movieRepository.save(movie);
    }

    public com.cfs.BMS.entity.Movie updateMovie(Long id, com.cfs.BMS.entity.Movie movie){

        com.cfs.BMS.entity.Movie old = movieRepository.findById(id).orElseThrow();

        old.setTitle(movie.getTitle());
        old.setDescription(movie.getDescription());
        old.setGenre(movie.getGenre());
        old.setLanguage(movie.getLanguage());
        old.setDurationMinutes(movie.getDurationMinutes());
        old.setRating(movie.getRating());
        old.setPosterUrl(movie.getPosterUrl());

        return movieRepository.save(old);
    }

    public void deleteMovie(Long id){
        movieRepository.deleteById(id);
    }

    // Theater
    public com.cfs.BMS.entity.Theater addTheater(com.cfs.BMS.entity.Theater theater){
        return theaterRepository.save(theater);
    }

    public com.cfs.BMS.entity.Theater updateTheater(Long id, com.cfs.BMS.entity.Theater theater){

        com.cfs.BMS.entity.Theater old = theaterRepository.findById(id).orElseThrow();

        old.setName(theater.getName());
        old.setAddress(theater.getAddress());

        return theaterRepository.save(old);
    }

    public void deleteTheater(Long id){
        theaterRepository.deleteById(id);
    }

    // Show
    public com.cfs.BMS.entity.Show addShow(com.cfs.BMS.entity.Show show){
        return showRepository.save(show);
    }

    public com.cfs.BMS.entity.Show updateShow(Long id, com.cfs.BMS.entity.Show show) {

        com.cfs.BMS.entity.Show old = showRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Show not found with id: " + id));

        old.setMovie(show.getMovie());
        old.setScreen(show.getScreen());
        old.setStartTime(show.getStartTime());
        old.setEndTime(show.getEndTime());
        old.setTicketPrice(show.getTicketPrice());

        return showRepository.save(old);
    }
    public void deleteShow(Long id){
        showRepository.deleteById(id);
    }
}
