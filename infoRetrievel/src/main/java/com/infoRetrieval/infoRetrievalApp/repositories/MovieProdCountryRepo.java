package com.infoRetrieval.infoRetrievalApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infoRetrieval.infoRetrievalApp.entities.Movie;
import com.infoRetrieval.infoRetrievalApp.entities.MovieProdCountries;

public interface MovieProdCountryRepo extends JpaRepository<MovieProdCountries, Long> {

    Optional<MovieProdCountries> findByMovie(Optional<Movie> movie);
    
}
