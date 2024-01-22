package com.infoRetrieval.infoRetrievalApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "movie_collection")
public class MovieCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mvCollectId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movieId", nullable = false)
    @JsonIgnore
    private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "collectionId", nullable = false)
    private Collection cllection;

    public MovieCollection() {
    }

    public MovieCollection(Movie movie, Collection cllection) {
        this.movie = movie;
        this.cllection = cllection;
    }

    public long getMvCollectId() {
        return mvCollectId;
    }

    public void setMvCollectId(long mvCollectId) {
        this.mvCollectId = mvCollectId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Collection getCllection() {
        return cllection;
    }

    public void setCllection(Collection cllection) {
        this.cllection = cllection;
    }

    @Override
    public String toString() {
        return "MovieCollection [mvCollectId=" + mvCollectId + ", movie=" + movie + ", cllection=" + cllection + "]";
    }
    
}
