package com.infoRetrieval.infoRetrievalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.infoRetrieval.infoRetrievalApp.repositories")
public class InfoRetrievalAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfoRetrievalAppApplication.class, args);
	}

}
