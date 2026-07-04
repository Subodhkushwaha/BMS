package com.cfs.BMS.service;

import com.cfs.BMS.entity.Movie;
import com.cfs.BMS.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    // Add Movie
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // Get All Movies
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // Get Movie By Id
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Movie not found with id: " + id));
    }

    // Search Movie
    public List<Movie> searchByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    // Get By Genre
    public List<Movie> getByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    // Get By Language
    public List<Movie> getByLanguage(String language) {
        return movieRepository.findByLanguage(language);
    }

    // Update Movie
    public Movie updateMovie(Long id, Movie movie) {

        Movie oldMovie = getMovieById(id);

        oldMovie.setTitle(movie.getTitle());
        oldMovie.setDescription(movie.getDescription());
        oldMovie.setGenre(movie.getGenre());
        oldMovie.setLanguage(movie.getLanguage());
        oldMovie.setDurationMinutes(movie.getDurationMinutes());
        oldMovie.setRating(movie.getRating());
        oldMovie.setPosterUrl(movie.getPosterUrl());

        return movieRepository.save(oldMovie);
    }

    // Delete Movie
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}