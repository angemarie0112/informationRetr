package com.infoRetrieval.infoRetrievalApp.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "movie", indexes = @Index(name = "movieIndex", columnList = "title, status, isAdult, mainLanguage, imdb_id", unique = false))
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long movieId;
    private String imdb_id;
    
    private String title;
    private String status;
    private double budget;

    @Column(columnDefinition = "TEXT")
    private String overview;

    private boolean isAdult;
    private String mainLanguage;
    private LocalDate releaseDate;
    private int duration;
    private String homepageLink;
    private String posterUrl;
    private int popularity;
    private int averageVotes;
    private int totalVotes;

    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MovieGenres> genres;

    @OneToOne(mappedBy = "movie", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private MovieCollection collections;

    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MovieProdCountries> productionCountries;

    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MovieProdCompanies> productionCompanies;

    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MovieLanguages> spokenLanguages;

    public Movie() {
    }

    public Movie(String imdb_id, String title, String status, double budget, String overview, boolean isAdult,
            String mainLanguage, LocalDate releaseDate, int duration, String homepageLink, String posterUrl,
            int popularity, int averageVotes, int totalVotes, List<MovieGenres> genres, MovieCollection collections,
            List<MovieProdCountries> productionCountries, List<MovieProdCompanies> productionCompanies,
            List<MovieLanguages> spokenLanguages) {
        this.imdb_id = imdb_id;
        this.title = title;
        this.status = status;
        this.budget = budget;
        this.overview = overview;
        this.isAdult = isAdult;
        this.mainLanguage = mainLanguage;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.homepageLink = homepageLink;
        this.posterUrl = posterUrl;
        this.popularity = popularity;
        this.averageVotes = averageVotes;
        this.totalVotes = totalVotes;
        this.genres = genres;
        this.collections = collections;
        this.productionCountries = productionCountries;
        this.productionCompanies = productionCompanies;
        this.spokenLanguages = spokenLanguages;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean isAdult) {
        this.isAdult = isAdult;
    }

    public String getMainLanguage() {
        return mainLanguage;
    }

    public void setMainLanguage(String mainLanguage) {
        this.mainLanguage = mainLanguage;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getHomepageLink() {
        return homepageLink;
    }

    public void setHomepageLink(String homepageLink) {
        this.homepageLink = homepageLink;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getAverageVotes() {
        return averageVotes;
    }

    public void setAverageVotes(int averageVotes) {
        this.averageVotes = averageVotes;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }

    public List<MovieGenres> getGenres() {
        return genres;
    }

    public void setGenres(List<MovieGenres> genres) {
        this.genres = genres;
    }

    public MovieCollection getCollections() {
        return collections;
    }

    public void setCollections(MovieCollection collections) {
        this.collections = collections;
    }

    public List<MovieProdCountries> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<MovieProdCountries> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public List<MovieProdCompanies> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<MovieProdCompanies> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<MovieLanguages> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<MovieLanguages> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    @Override
    public String toString() {
        return "Movie [movieId=" + movieId + ", imdb_id=" + imdb_id + ", title=" + title + ", status=" + status
                + ", budget=" + budget + ", overview=" + overview + ", isAdult=" + isAdult + ", mainLanguage="
                + mainLanguage + ", releaseDate=" + releaseDate + ", duration=" + duration + ", homepageLink="
                + homepageLink + ", posterUrl=" + posterUrl + ", popularity=" + popularity + ", averageVotes="
                + averageVotes + ", totalVotes=" + totalVotes + ", genres=" + genres + ", collections=" + collections
                + ", productionCountries=" + productionCountries + ", productionCompanies=" + productionCompanies
                + ", spokenLanguages=" + spokenLanguages + "]";
    }

}
