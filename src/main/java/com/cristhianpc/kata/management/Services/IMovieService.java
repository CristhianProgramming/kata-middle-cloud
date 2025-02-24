package com.cristhianpc.kata.management.Services;

import com.cristhianpc.kata.management.Models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IMovieService {
    Page<Movie> getAllMovies(PageRequest pageRequest);

    Movie getMovieByField(Long id);

    Movie createMovie(Movie movie);

    Movie updateMovie(Long id, Movie movie) throws Exception;

    void deletMovie(Long id) throws Exception;
}
