package com.infoRetrieval.infoRetrievalApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infoRetrieval.infoRetrievalApp.entities.Genres;

public interface GenresRepo extends JpaRepository<Genres, Long> {

    Optional<Genres> findByName(String name);

}
