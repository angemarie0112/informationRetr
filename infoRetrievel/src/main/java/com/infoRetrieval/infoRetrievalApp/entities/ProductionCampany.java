package com.infoRetrieval.infoRetrievalApp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "productionCampany")
public class ProductionCampany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long prodCompanyId;
    private String name;

    public ProductionCampany() {
    }

    public ProductionCampany(String name) {
        this.name = name;
    }

    public long getProdCompanyId() {
        return prodCompanyId;
    }

    public void setProdCompanyId(long prodCompanyId) {
        this.prodCompanyId = prodCompanyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductionCampany [prodCompanyId=" + prodCompanyId + ", name=" + name + "]";
    }

}
