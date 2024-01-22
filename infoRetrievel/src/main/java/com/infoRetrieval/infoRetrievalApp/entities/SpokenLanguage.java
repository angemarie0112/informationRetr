package com.infoRetrieval.infoRetrievalApp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "spokenLanguage")
public class SpokenLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long langId;
    private String iso_code;
    private String languageName;

    public SpokenLanguage() {
    }

    public SpokenLanguage(String iso_code, String languageName) {
        this.iso_code = iso_code;
        this.languageName = languageName;
    }

    public long getLangId() {
        return langId;
    }

    public void setLangId(long langId) {
        this.langId = langId;
    }

    public String getIso_code() {
        return iso_code;
    }

    public void setIso_code(String iso_code) {
        this.iso_code = iso_code;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    @Override
    public String toString() {
        return "SpokenLanguage [langId=" + langId + ", iso_code=" + iso_code + ", languageName=" + languageName + "]";
    }
    
}
