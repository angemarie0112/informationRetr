package com.infoRetrieval.infoRetrievalApp.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.infoRetrieval.infoRetrievalApp.entities.Collection;
import com.infoRetrieval.infoRetrievalApp.entities.Genres;
import com.infoRetrieval.infoRetrievalApp.entities.Movie;
import com.infoRetrieval.infoRetrievalApp.entities.MovieCollection;
import com.infoRetrieval.infoRetrievalApp.entities.MovieGenres;
import com.infoRetrieval.infoRetrievalApp.entities.MovieLanguages;
import com.infoRetrieval.infoRetrievalApp.entities.MovieProdCompanies;
import com.infoRetrieval.infoRetrievalApp.entities.MovieProdCountries;
import com.infoRetrieval.infoRetrievalApp.entities.ProductionCampany;
import com.infoRetrieval.infoRetrievalApp.entities.ProductionCountry;
import com.infoRetrieval.infoRetrievalApp.entities.SpokenLanguage;
import com.infoRetrieval.infoRetrievalApp.repositories.CollectionRepo;
import com.infoRetrieval.infoRetrievalApp.repositories.GenresRepo;
import com.infoRetrieval.infoRetrievalApp.repositories.MovieCollectionRepo;
import com.infoRetrieval.infoRetrievalApp.repositories.MovieGenreRepo;
import com.infoRetrieval.infoRetrievalApp.repositories.MovieLanguageRepo;
import com.infoRetrieval.infoRetrievalApp.repositories.MovieProdCompanyRepo;
import com.infoRetrieval.infoRetrievalApp.repositories.MovieProdCountryRepo;
import com.infoRetrieval.infoRetrievalApp.repositories.MovieRepo;
import com.infoRetrieval.infoRetrievalApp.repositories.ProductionCampanyRepo;
import com.infoRetrieval.infoRetrievalApp.repositories.ProductionCountryRepo;
import com.infoRetrieval.infoRetrievalApp.repositories.SpokenLanguageRepo;

@Service
public class MovieService {

    @Autowired
    private MovieRepo mvRepo;

    @Autowired
    private CollectionRepo collectRepo;

    @Autowired
    private MovieCollectionRepo mvCollectRepo;

    @Autowired
    private GenresRepo genreRepo;

    @Autowired
    private MovieGenreRepo mvGenreRepo;

    @Autowired
    private ProductionCampanyRepo prodCompanyRepo;

    @Autowired
    private MovieProdCompanyRepo mvProdCompanyRepo;

    @Autowired
    private ProductionCountryRepo prodCountryRepo;

    @Autowired
    private MovieProdCountryRepo mvProdCountryRepo;

    @Autowired
    private SpokenLanguageRepo langRepo;

    @Autowired
    private MovieLanguageRepo mvSpokenLang;

    // create movie record
    public long setMovie(Movie movie) {
        try {
            // check for duplicate movie
            Optional<Movie> mvCheck = this.mvRepo.findByImdbId(movie.getImdb_id());
            if (mvCheck.isPresent())
                return 1; // movie already exist

            // store the new movie
            Movie storedMovie = this.mvRepo.save(movie);
            if (storedMovie.equals(null))
                return 3; // failed to store move

            // return the stored movie id
            return 1;

        } catch (Exception e) {
            return 0; // an exception occur
        }
    }

    // create movie record
    public long setMovieCollection(String imdbId, String collectionName) {
        try {
            // check for the movie
            Optional<Movie> movie = this.mvRepo.findByImdbId(imdbId);
            if (!movie.isPresent())
                return 2; // movie not found

            // check for the collection
            Optional<Collection> collect = this.collectRepo.findByName(collectionName);
            if (!collect.isPresent())
                return 3; // movie collection not found

            // check for duplicate movieCollection
            Optional<MovieCollection> check = this.mvCollectRepo.findByMovie(movie);
            if (check.isPresent())
                return 4; // collection already exist for the movie

            // store the new movie collection
            MovieCollection mvCollect = new MovieCollection();
            mvCollect.setCllection(collect.get());
            mvCollect.setMovie(movie.get());

            MovieCollection storedMvCollect = this.mvCollectRepo.save(mvCollect);
            if (storedMvCollect.equals(null))
                return 5; // failed to store movie collection

            // return the stored movie id
            return storedMvCollect.getMvCollectId();

        } catch (Exception e) {
            return 0; // an exception occur
        }
    }

    public long setMovieGenre(String imdbId, String genreName) {
        try {
            // check for the movie
            Optional<Movie> movie = this.mvRepo.findByImdbId(imdbId);
            if (!movie.isPresent())
                return 2; // movie not found

            // check for the genre
            Optional<Genres> genre = this.genreRepo.findByName(genreName);
            if (!genre.isPresent())
                return 3; // movie genre not found

            // check for duplicate movieGenre
            Optional<MovieGenres> check = this.mvGenreRepo.findByMovie(movie);
            if (check.isPresent())
                return 4; // genra already exist for the movie

            // store the new movie genre
            MovieGenres tmpData = new MovieGenres();
            tmpData.setGenre(genre.get());
            tmpData.setMovie(movie.get());

            MovieGenres storedData = this.mvGenreRepo.save(tmpData);
            if (storedData.equals(null))
                return 5; // failed to store movie genre

            // return the stored movie id
            return storedData.getMvGenId();

        } catch (Exception e) {
            return 0; // an exception occur
        }
    }

    public long setMovieProdCompany(String imdbId, String prodCompanyName) {
        try {
            // check for the movie
            Optional<Movie> movie = this.mvRepo.findByImdbId(imdbId);
            if (!movie.isPresent())
                return 2; // movie not found

            // check for the production company
            Optional<ProductionCampany> prodCompany = this.prodCompanyRepo.findByName(prodCompanyName);
            if (!prodCompany.isPresent())
                return 3; // movie genre not found

            // check for duplicate movie production company
            Optional<MovieProdCompanies> check = this.mvProdCompanyRepo.findByMovie(movie);
            if (check.isPresent())
                return 4; // prod company already exist for the movie

            // store the new production company
            MovieProdCompanies tmpData = new MovieProdCompanies();
            tmpData.setProdCompany(prodCompany.get());
            tmpData.setMovie(movie.get());

            MovieProdCompanies storedData = this.mvProdCompanyRepo.save(tmpData);
            if (storedData.equals(null))
                return 5; // failed to store movie production company

            // return the stored id
            return storedData.getMvProdCompanyId();

        } catch (Exception e) {
            return 0; // an exception occur
        }
    }

    public long setMovieProdCountry(String imdbId, String countryIsoCode) {
        try {
            // check for the movie
            Optional<Movie> movie = this.mvRepo.findByImdbId(imdbId);
            if (!movie.isPresent())
                return 2; // movie not found

            // check for the production country
            Optional<ProductionCountry> prodCountry = this.prodCountryRepo.findByCountryCode(countryIsoCode);
            if (!prodCountry.isPresent())
                return 3; // movie prodCountry not found

            // check for duplicate movie production country
            Optional<MovieProdCountries> check = this.mvProdCountryRepo.findByMovie(movie);
            if (check.isPresent())
                return 4; // prod company already exist for the movie

            // store the new production company
            MovieProdCountries tmpData = new MovieProdCountries();
            tmpData.setProdCountry(prodCountry.get());
            tmpData.setMovie(movie.get());

            MovieProdCountries storedData = this.mvProdCountryRepo.save(tmpData);
            if (storedData.equals(null))
                return 5; // failed to store movie production country

            // return the stored id
            return storedData.getMvProdCountryId();

        } catch (Exception e) {
            return 0; // an exception occur
        }
    }

    public long setMovieLanguage(String imdbId, String langCode) {
        try {
            // check for the movie
            Optional<Movie> movie = this.mvRepo.findByImdbId(imdbId);
            if (!movie.isPresent())
                return 2; // movie not found

            // check for the languages
            Optional<SpokenLanguage> spokenLang = this.langRepo.findByIsoCode(langCode);
            if (!spokenLang.isPresent())
                return 3; // movie language not found

            // check for duplicate movie spoken language
            Optional<MovieLanguages> check = this.mvSpokenLang.findByMovie(movie);
            if (check.isPresent())
                return 4; // language already exist for the movie

            // store the new production company
            MovieLanguages tmpData = new MovieLanguages();
            tmpData.setLanguages(spokenLang.get());
            tmpData.setMovie(movie.get());

            MovieLanguages storedData = this.mvSpokenLang.save(tmpData);
            if (storedData.equals(null))
                return 5; // failed to store movie language

            // return the stored id
            return storedData.getMvLangId();

        } catch (Exception e) {
            return 0; // an exception occur
        }
    }

    public ResponseEntity<Map<String, Object>> searchMovies(int page, int size, boolean isAdult, String title,
        String status, String language, String genre, String prodCountry, String prodCompany,
        String collectionName) 
    {
        try {

			// set the page object
			Pageable paging = PageRequest.of(page, size);

			// fetch the movie form the datasource
			Page<Movie> pagedMovies = this.mvRepo.findAllWithFilters(paging, isAdult, title, status,language, genre, prodCountry, prodCompany, collectionName);

			// set the response using a hasmap
			Map<String, Object> res = new HashMap<>();
			res.put("movies", pagedMovies.getContent());
			res.put("currentPage", pagedMovies.getNumber());
			res.put("totalPages", pagedMovies.getTotalPages());
			res.put("totalItems", pagedMovies.getTotalElements());

			// update and return the response entity
			return new ResponseEntity<>(res, HttpStatus.OK);

		} catch (Exception e) {
			//throw new UnsupportedOperationException("Unimplemented method 'searchMovies'");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

}
