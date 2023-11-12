package com.apapedia.catalogue.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.apapedia.catalogue.model.Catalogue;
import com.apapedia.catalogue.repository.CatalogueDb;

@Service
public class CatalogueServiceImpl implements CatalogueService{

    @Autowired
    CatalogueDb catalogueDb;

    @Override
    public void createCatalogue(Catalogue catalogue) {
        catalogueDb.save(catalogue);
    }
    
}
