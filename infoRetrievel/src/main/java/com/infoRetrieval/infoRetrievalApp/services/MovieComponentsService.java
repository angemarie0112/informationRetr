package com.infoRetrieval.infoRetrievalApp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infoRetrieval.infoRetrievalApp.entities.Collection;
import com.infoRetrieval.infoRetrievalApp.entities.Genres;
import com.infoRetrieval.infoRetrievalApp.entities.ProductionCampany;
import com.infoRetrieval.infoRetrievalApp.entities.ProductionCountry;
import com.infoRetrieval.infoRetrievalApp.entities.SpokenLanguage;
import com.infoRetrieval.infoRetrievalApp.repositories.CollectionRepo;
import com.infoRetrieval.infoRetrievalApp.repositories.GenresRepo;
import com.infoRetrieval.infoRetrievalApp.repositories.ProductionCampanyRepo;
import com.infoRetrieval.infoRetrievalApp.repositories.ProductionCountryRepo;
import com.infoRetrieval.infoRetrievalApp.repositories.SpokenLanguageRepo;

@Service
public class MovieComponentsService {
    @Autowired
    private SpokenLanguageRepo langRepo;

    @Autowired
    private ProductionCampanyRepo prodCompRepo;

    @Autowired
    private ProductionCountryRepo prodCountryRepo;

    @Autowired
    private CollectionRepo collectRepo;

    @Autowired
    private GenresRepo genreRepo;
    
    /** languages */
    public long addLanguage(SpokenLanguage newLang) {
        try {
            // check for duplicate 
            Optional<SpokenLanguage> langCheck = this.langRepo.findByIsoCode(newLang.getIso_code());
            if(langCheck.isPresent()) return 2; // language already created

            // store the language
            SpokenLanguage savedLang = langRepo.save(newLang);
            if(savedLang.equals(null)) return 3; // failed to save new language

            // return the language id
            return savedLang.getLangId();

        } catch (Exception e) {
            return 0;
        }
    }

    /** production companies */
    public long addProdCompany(ProductionCampany newProdCompany) {
        try {
            // check for duplicate 
            Optional<ProductionCampany> prodCompChk = this.prodCompRepo.findByName(newProdCompany.getName());
            if(prodCompChk.isPresent()) return 2; // company already created

            // store the new record
            ProductionCampany savedCompany = prodCompRepo.save(newProdCompany);
            if(savedCompany.equals(null)) return 3; // failed to save new record

            // return the record id
            return savedCompany.getProdCompanyId();

        } catch (Exception e) {
            return 0;
        }
    }

    /** production country */
    public long addProdCountry(ProductionCountry newProdCountry) {
        try {
            // check for duplicate 
            Optional<ProductionCountry> prodCountryChk = this.prodCountryRepo.findByCountryCode(newProdCountry.getIso_ii_code());
            if(prodCountryChk.isPresent()) return 2; // company already created

            // store the new record
            ProductionCountry savedCountry = prodCountryRepo.save(newProdCountry);
            if(savedCountry.equals(null)) return 3; // failed to save new record

            // return the record id
            return savedCountry.getProdCountryId();

        } catch (Exception e) {
            return 0;
        }
    }

    /** collection */
    public long addCollection(Collection newCollection) {
        try {
            // check for duplicate 
            Optional<Collection> collectChk = this.collectRepo.findByName(newCollection.getName());
            if(collectChk.isPresent()) return 2; // collection already created

            // store the new record
            Collection savedCollection = this.collectRepo.save(newCollection);
            if(savedCollection.equals(null)) return 3; // failed to save new record

            // return the record id
            return savedCollection.getCollectionId();

        } catch (Exception e) {
            return 0;
        }
    }

    /** add genre */
    public long addGenre(Genres newGenre) {
        try {
            // check for duplicate 
            Optional<Genres> genreChk = this.genreRepo.findByName(newGenre.getName());
            if(genreChk.isPresent()) return 2; // collection already created

            // store the new record
            Genres savedGenre = this.genreRepo.save(newGenre);
            if(savedGenre.equals(null)) return 3; // failed to save new record

            // return the record id
            return savedGenre.getGenreId();

        } catch (Exception e) {
            return 0;
        }
    }
}
