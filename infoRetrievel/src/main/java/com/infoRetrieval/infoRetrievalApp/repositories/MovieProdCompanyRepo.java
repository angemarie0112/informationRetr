package com.infoRetrieval.infoRetrievalApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infoRetrieval.infoRetrievalApp.entities.Movie;
import com.infoRetrieval.infoRetrievalApp.entities.MovieProdCompanies;

public interface MovieProdCompanyRepo extends JpaRepository<MovieProdCompanies, Long> {

    Optional<MovieProdCompanies> findByMovie(Optional<Movie> movie);
    
}
