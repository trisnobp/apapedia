package com.apapedia.catalogue.restcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
}
