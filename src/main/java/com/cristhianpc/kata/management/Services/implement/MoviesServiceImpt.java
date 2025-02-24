package com.cristhianpc.kata.management.Services.implement;

import com.cristhianpc.kata.management.Models.Movie;
import com.cristhianpc.kata.management.Repositories.IMovieRepository;
import com.cristhianpc.kata.management.Services.IMovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MoviesServiceImpt implements IMovieService {

    private final IMovieRepository movieRepository;

    public MoviesServiceImpt(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<?> getAllMovies(PageRequest pageRequest) {
        return movieRepository.findAll(pageRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public Movie getMovieByField(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Long id, Movie movie) throws Exception {
        Movie exist = getMovieByField(id);
        if (exist == null) {
            throw new Exception("Movie not found");
        }
        exist.setGender(movie.getGender());
        exist.setDuration(movie.getDuration());
        exist.setClassification(movie.getClassification());
        exist.setName(movie.getName());
        exist.setDescription(movie.getDescription());
        exist.setImageUrl(movie.getImageUrl());
        return movieRepository.save(exist);
    }

    @Override
    public void deletMovie(Long id) throws Exception {
        Movie exist = getMovieByField(id);
        if (exist == null) {
            throw new Exception("Movie not found");
        }
        movieRepository.deleteById(id);
    }
}
