package com.infoRetrieval.infoRetrievalApp.entities;

import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;

@Component
public class SearchIndexBuild {
    public final EntityManager entityManager;

    public SearchIndexBuild(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // public void indexPersistedData() throws IndexException {
    //     try {
    //         FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
    //         fullTextEntityManager.createIndexer().startAndWait();
    //     } catch (InterruptedException e) {
    //         throw new IndexException()
    //     }
    // }
}
