package com.infoRetrieval.infoRetrievalApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infoRetrieval.infoRetrievalApp.entities.ProductionCampany;

public interface ProductionCampanyRepo extends JpaRepository<ProductionCampany, Long> {

    Optional<ProductionCampany> findByName(String name);

}
