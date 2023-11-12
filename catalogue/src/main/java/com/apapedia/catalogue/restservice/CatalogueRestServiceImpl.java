package com.apapedia.catalogue.restservice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.apapedia.catalogue.model.Catalogue;
import com.apapedia.catalogue.repository.CatalogueDb;

@Service
public class CatalogueRestServiceImpl implements CatalogueRestService{

    @Autowired
    CatalogueDb catalogueDb;
    
    @Override
    public void createRestCatalogue(Catalogue catalogue) {
        catalogueDb.save(catalogue);
    }
    
}
