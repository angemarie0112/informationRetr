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
@Table(name = "movie_prod_companies")
public class MovieProdCompanies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mvProdCompanyId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movieId", nullable = false)
    @JsonIgnore
    private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "prodCompanyId", nullable = false)
    private ProductionCampany prodCompany;

    public MovieProdCompanies() {
    }

    public MovieProdCompanies(Movie movie, ProductionCampany prodCompany) {
        this.movie = movie;
        this.prodCompany = prodCompany;
    }

    public long getMvProdCompanyId() {
        return mvProdCompanyId;
    }

    public void setMvProdCompanyId(long mvProdCompanyId) {
        this.mvProdCompanyId = mvProdCompanyId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public ProductionCampany getProdCompany() {
        return prodCompany;
    }

    public void setProdCompany(ProductionCampany prodCompany) {
        this.prodCompany = prodCompany;
    }

    @Override
    public String toString() {
        return "MovieProdCompanies [mvProdCompanyId=" + mvProdCompanyId + ", movie=" + movie + ", prodCompany="
                + prodCompany + "]";
    }
}
