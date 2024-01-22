package com.infoRetrieval.infoRetrievalApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.infoRetrieval.infoRetrievalApp.entities.SpokenLanguage;

public interface SpokenLanguageRepo extends JpaRepository<SpokenLanguage, Long> {

    @Query("select s from SpokenLanguage s where s.iso_code = ?1")
    Optional<SpokenLanguage> findByIsoCode(String iso_code);

}
