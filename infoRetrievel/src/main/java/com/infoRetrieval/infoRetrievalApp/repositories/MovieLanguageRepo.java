package com.infoRetrieval.infoRetrievalApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infoRetrieval.infoRetrievalApp.entities.Movie;
import com.infoRetrieval.infoRetrievalApp.entities.MovieLanguages;

public interface MovieLanguageRepo extends JpaRepository<MovieLanguages, Long> {

    Optional<MovieLanguages> findByMovie(Optional<Movie> movie);

}
