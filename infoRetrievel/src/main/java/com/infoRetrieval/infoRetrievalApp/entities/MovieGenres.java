package com.infoRetrieval.infoRetrievalApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "movie_genres")
public class MovieGenres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mvGenId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movieId", nullable = false)
    @JsonIgnore
    private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "genreId", nullable = false)
    private Genres genre;

    public MovieGenres() {
    }

    public MovieGenres(Movie movie, Genres genre) {
        this.movie = movie;
        this.genre = genre;
    }

    public long getMvGenId() {
        return mvGenId;
    }

    public void setMvGenId(long mvGenId) {
        this.mvGenId = mvGenId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Genres getGenre() {
        return genre;
    }

    public void setGenre(Genres genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "MovieGenres [mvGenId=" + mvGenId + ", movie=" + movie + ", genre=" + genre + "]";
    }

}
