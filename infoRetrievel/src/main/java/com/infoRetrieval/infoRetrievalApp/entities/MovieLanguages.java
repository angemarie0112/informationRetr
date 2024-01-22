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
@Table(name = "movie_languages")
public class MovieLanguages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mvLangId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movieId", nullable = false)
    @JsonIgnore
    private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "langId", nullable = false)
    private SpokenLanguage languages;

    public MovieLanguages() {
    }

    public MovieLanguages(Movie movie, SpokenLanguage languages) {
        this.movie = movie;
        this.languages = languages;
    }

    public long getMvLangId() {
        return mvLangId;
    }

    public void setMvLangId(long mvLangId) {
        this.mvLangId = mvLangId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public SpokenLanguage getLanguages() {
        return languages;
    }

    public void setLanguages(SpokenLanguage languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "MovieLanguages [mvLangId=" + mvLangId + ", movie=" + movie + ", languages=" + languages + "]";
    }
}
