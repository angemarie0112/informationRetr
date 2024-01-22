package com.infoRetrieval.infoRetrievalApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infoRetrieval.infoRetrievalApp.entities.Collection;

public interface CollectionRepo extends JpaRepository<Collection, Long> {

    Optional<Collection> findByName(String name);

}
