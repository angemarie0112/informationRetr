package com.infoRetrieval.infoRetrievalApp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "collection")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long collectionId;
    private String name;
    private String posterPath;
    private String backdropPath;

    public Collection() {
    }

    public Collection(String name, String posterPath, String backdropPath) {
        this.name = name;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
    }

    public long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(long collectionId) {
        this.collectionId = collectionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    @Override
    public String toString() {
        return "Collection [collectionId=" + collectionId + ", name=" + name + ", posterPath=" + posterPath
                + ", backdropPath=" + backdropPath + "]";
    }

}
