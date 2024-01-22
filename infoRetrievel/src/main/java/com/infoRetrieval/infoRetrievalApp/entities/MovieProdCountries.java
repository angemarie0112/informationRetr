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
@Table(name = "movie_prod_country")
public class MovieProdCountries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mvProdCountryId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movieId", nullable = false)
    @JsonIgnore
    private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "prodCountryId", nullable = false)
    private ProductionCountry prodCountry;

    public MovieProdCountries(){}

    public MovieProdCountries(Movie movie, ProductionCountry prodCountry) {
        this.movie = movie;
        this.prodCountry = prodCountry;
    }

    public long getMvProdCountryId() {
        return mvProdCountryId;
    }

    public void setMvProdCountryId(long mvProdCountryId) {
        this.mvProdCountryId = mvProdCountryId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public ProductionCountry getProdCountry() {
        return prodCountry;
    }

    public void setProdCountry(ProductionCountry prodCountry) {
        this.prodCountry = prodCountry;
    }

    @Override
    public String toString() {
        return "MovieProdCountries [mvProdCountryId=" + mvProdCountryId + ", movie=" + movie + ", prodCountry="
                + prodCountry + "]";
    }
}
