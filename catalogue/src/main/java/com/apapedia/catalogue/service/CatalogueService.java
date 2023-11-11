package com.apapedia.catalogue.service;


import com.apapedia.catalogue.model.Catalogue;
import java.util.UUID;

public interface CatalogueService {

    public void createCatalogue(Catalogue catalogue);

    public void deleteCatalogue(Catalogue catalogue);

    public Catalogue getCatalogueById(UUID id);
    
}
