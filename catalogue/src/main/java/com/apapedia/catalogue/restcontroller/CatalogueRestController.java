package com.apapedia.catalogue.restcontroller;

import java.util.List;

import java.util.UUID;
import org.hibernate.validator.constraints.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.apapedia.catalogue.model.Catalogue;
import com.apapedia.catalogue.restservice.CatalogueRestService;

@RestController
@RequestMapping("/api")
public class CatalogueRestController {

    @Autowired
    private CatalogueRestService catalogueRestService;

    @GetMapping(value = "/catalogue/view-all")
    private List<Catalogue> retrieveAllCatalogue() {
        return catalogueRestService.retrieveRestAllCatalogue();
    }

    @GetMapping("/catalogue/{id}")
    public ResponseEntity<Catalogue> retrieveCatalogue(@PathVariable UUID id) {
        return catalogueRestService.getRestCatalogueById(id)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Catalogue with ID " + id + " not found"
            ));
    }
    
}
