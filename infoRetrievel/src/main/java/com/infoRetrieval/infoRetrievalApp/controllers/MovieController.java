package com.infoRetrieval.infoRetrievalApp.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infoRetrieval.infoRetrievalApp.entities.Movie;
import com.infoRetrieval.infoRetrievalApp.services.MovieService;

@RestController
@RequestMapping("movieCtrl/v1/")
@CrossOrigin(origins = "*")
public class MovieController {

    @Autowired
    private MovieService mvServ;

    @GetMapping("test")
    public String testMapper() {
        return "This is just a test route";
    }

    @PostMapping("addMovie")
    public long createMovieData(@RequestBody Movie movie) {
        return this.mvServ.setMovie(movie);
    }

    @PostMapping("addMovieCollection")
    public long createMovieCollection(@RequestBody Map<String, Object> collectData) {
        String mvImdb = (String) collectData.get("mvImdb");
        String collectionName = (String) collectData.get("collectionName");
        return this.mvServ.setMovieCollection(mvImdb, collectionName);
    }

    @PostMapping("addMovieGenre")
    public long createMovieGenre(@RequestBody Map<String, Object> genreData) {
        String mvImdb = (String) genreData.get("mvImdb");
        String genreName = (String) genreData.get("genreName");
        return this.mvServ.setMovieGenre(mvImdb, genreName);
    }

    @PostMapping("addMovieProdCountry")
    public long createMovieProdCountry(@RequestBody Map<String, Object> countryData) {
        String mvImdb = (String) countryData.get("mvImdb");
        String countryIso = (String) countryData.get("countryIso");
        return this.mvServ.setMovieProdCountry(mvImdb, countryIso);
    }

    @PostMapping("addMovieProdCompany")
    public long createMovieProdCompany(@RequestBody Map<String, Object> companyData) {
        String mvImdb = (String) companyData.get("mvImdb");
        String companyName = (String) companyData.get("companyName");
        return this.mvServ.setMovieProdCompany(mvImdb, companyName);
    }

    @PostMapping("addMovieLanguage")
    public long createMovieLanguages(@RequestBody Map<String, Object> languageData) {
        String mvImdb = (String) languageData.get("mvImdb");
        String langCode = (String) languageData.get("langCode");
        return this.mvServ.setMovieLanguage(mvImdb, langCode);
    }

    // the search function 
    @PostMapping("searchMovies")
	public ResponseEntity<Map<String, Object>> pagedMovieSearch (@RequestBody Map<String, Object> searchData) 
    {
        int page = searchData.get("page") != null ? (int) searchData.get("page") : 1;
        int size = searchData.get("size") != null ? (int) searchData.get("size") : 10;
        boolean isAdult = searchData.get("isAdult") != null ? (boolean) searchData.get("isAdult") : false;
        String title = (String) searchData.get("title");
        String status = (String) searchData.get("status");
        String language = (String) searchData.get("language");
        String genre = (String) searchData.get("genre");
        String prodCountry = (String) searchData.get("prodCountry");
        String prodCompany = (String) searchData.get("prodCompany");
        String collectionName = (String) searchData.get("collectionName");

		return this.mvServ.searchMovies(
			page, 
			size,
			isAdult,
			title,
			status,
            language,
            genre,
            prodCountry,
            prodCompany,
            collectionName
			);
	}

}
