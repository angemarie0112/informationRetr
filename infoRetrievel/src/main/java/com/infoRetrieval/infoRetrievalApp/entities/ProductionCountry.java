package com.infoRetrieval.infoRetrievalApp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "productionCountry")
public class ProductionCountry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long prodCountryId;
    private String iso_ii_code;
    private String countryName;

    public ProductionCountry() {
    }

    public ProductionCountry(String iso_ii_code, String countryName) {
        this.iso_ii_code = iso_ii_code;
        this.countryName = countryName;
    }

    public long getProdCountryId() {
        return prodCountryId;
    }

    public void setProdCountryId(long prodCountryId) {
        this.prodCountryId = prodCountryId;
    }

    public String getIso_ii_code() {
        return iso_ii_code;
    }

    public void setIso_ii_code(String iso_ii_code) {
        this.iso_ii_code = iso_ii_code;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "ProductionCountry [prodCountryId=" + prodCountryId + ", iso_ii_code=" + iso_ii_code + ", countryName="
                + countryName + "]";
    }

}
