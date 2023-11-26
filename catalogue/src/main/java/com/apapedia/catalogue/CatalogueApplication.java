package com.apapedia.catalogue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.apapedia.catalogue.service.CategoryService;

@SpringBootApplication
public class CatalogueApplication {

	@Autowired
    private CategoryService categoryService; 

	public static void main(String[] args) {
		SpringApplication.run(CatalogueApplication.class, args);
	}

	@Bean
    public CommandLineRunner initializeCategories() {
        return args -> {
            categoryService.init();
        };
    }


}
