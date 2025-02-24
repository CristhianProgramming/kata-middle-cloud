package com.cristhianpc.kata.management.Controller;

import com.cristhianpc.kata.management.Models.Movie;
import com.cristhianpc.kata.management.Services.IMovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/movies")
public class MovieController {

    private final IMovieService movieService;

    public MovieController(IMovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("")
    ResponseEntity<Page<Movie>> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int limit
    ) {
        Page<Movie> response = movieService.getAllMovies(PageRequest.of(page, limit));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    ResponseEntity<Movie> getMovieById(
            @PathVariable(name = "id") Long id_movie
    ) {
        Movie response = movieService.getMovieByField(id_movie);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    ResponseEntity<Movie> createMovie(
            @RequestBody Movie payload
    ) {
        Movie response = movieService.createMovie(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    ResponseEntity<Movie> updateMovie(
            @PathVariable(name = "id") Long id_movie,
            @RequestBody Movie payload
    ) throws Exception {
        Movie response = movieService.updateMovie(id_movie, payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteMovie(
            @PathVariable(name = "id") Long id_movie
    ) throws Exception {
        movieService.deletMovie(id_movie);
        return ResponseEntity.noContent().build();
    }
}
