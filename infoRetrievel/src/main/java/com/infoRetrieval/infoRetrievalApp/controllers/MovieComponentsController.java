package com.infoRetrieval.infoRetrievalApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infoRetrieval.infoRetrievalApp.entities.Collection;
import com.infoRetrieval.infoRetrievalApp.entities.Genres;
import com.infoRetrieval.infoRetrievalApp.entities.ProductionCampany;
import com.infoRetrieval.infoRetrievalApp.entities.ProductionCountry;
import com.infoRetrieval.infoRetrievalApp.entities.SpokenLanguage;
import com.infoRetrieval.infoRetrievalApp.services.MovieComponentsService;

@RestController
@RequestMapping("movieCompCtrl/v1/")
@CrossOrigin(origins = "*")
public class MovieComponentsController {
    @Autowired
    private MovieComponentsService compServ;

    @GetMapping("test")
    public String test() {
        return "this is the movie component test route";
    }

    @PostMapping("addLanguage")
    public long addLanguage(@RequestBody SpokenLanguage lang)
    {
        return this.compServ.addLanguage(lang);
    }

    @PostMapping("addProdCompany")
    public long addProdCompany(@RequestBody ProductionCampany prodCompany)
    {
        return this.compServ.addProdCompany(prodCompany);
    }

    @PostMapping("addProdCountry")
    public long addProdCountry(@RequestBody ProductionCountry prodCountry)
    {
        return this.compServ.addProdCountry(prodCountry);
    }

    @PostMapping("addCollection")
    public long addCollection(@RequestBody Collection collection)
    {
        return this.compServ.addCollection(collection);
    }

    @PostMapping("addGenre")
    public long addGenre(@RequestBody Genres genre)
    {
        return this.compServ.addGenre(genre);
    }
    
}
