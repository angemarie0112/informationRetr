package com.infoRetrieval.infoRetrievalApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.infoRetrieval.infoRetrievalApp.entities.ProductionCountry;

public interface ProductionCountryRepo extends JpaRepository<ProductionCountry, Long> {

    @Query("select pc from ProductionCountry pc where pc.iso_ii_code = ?1")
    Optional<ProductionCountry> findByCountryCode(String iso_ii_code);

}
