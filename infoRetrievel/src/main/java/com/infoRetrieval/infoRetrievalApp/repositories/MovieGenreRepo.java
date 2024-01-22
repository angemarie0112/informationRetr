package com.infoRetrieval.infoRetrievalApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infoRetrieval.infoRetrievalApp.entities.Movie;
import com.infoRetrieval.infoRetrievalApp.entities.MovieGenres;

public interface MovieGenreRepo extends JpaRepository<MovieGenres, Long> {

    Optional<MovieGenres> findByMovie(Optional<Movie> movie);
    
}
