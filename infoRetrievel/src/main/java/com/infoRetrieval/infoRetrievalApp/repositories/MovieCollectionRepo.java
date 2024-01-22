package com.infoRetrieval.infoRetrievalApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infoRetrieval.infoRetrievalApp.entities.Movie;
import com.infoRetrieval.infoRetrievalApp.entities.MovieCollection;

public interface MovieCollectionRepo extends JpaRepository<MovieCollection, Long> {

    Optional<MovieCollection> findByMovie(Optional<Movie> movie);
    
}
