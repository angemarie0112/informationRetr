package com.infoRetrieval.infoRetrievalApp.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.infoRetrieval.infoRetrievalApp.entities.Movie;

public interface MovieRepo extends JpaRepository<Movie, Long> {

    @Query("select mv from Movie mv where mv.imdb_id = ?1")
    Optional<Movie> findByImdbId(String imdb_id);

    // and (?4 is null or m.spokenLanguages.languages.languageName LIKE %?4%) and (?5 is null or m.genres.genre.name LIKE %?5%) and (?6 is null or m.productionCountries.prodCountry.countryName LIKE %?6%) and (?7 is null or m.productionCompanies.prodCompany.name LIKE %?7%) and (?8 is null or m.collections.cllection.name LIKE %?8%)
    @Query("select m from Movie m where (?1 is null or m.isAdult = ?1) and (?2 is null or m.title LIKE %?2%) and (?3 is null or m.status LIKE %?3%)")
    Page<Movie> findAllWithFilters(Pageable paging, boolean isAdult, String title, String status, String language, String genre, String prodCountry, String prodCompany, String collectionName);

}
