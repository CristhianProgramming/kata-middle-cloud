package com.cristhianpc.kata.management.Repositories;

import com.cristhianpc.kata.management.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieRepository extends JpaRepository<Movie,Long> {
}
