package com.apapedia.catalogue.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.apapedia.catalogue.model.Catalogue;
import com.apapedia.catalogue.repository.CatalogueDb;

@Service
public class CatalogueServiceImpl implements CatalogueService {
    @Autowired
    CatalogueDb catalogueDb;

    @Override
    @Transactional
    public void createCatalogue(Catalogue catalogue){
        catalogueDb.save(catalogue);
    }
    
}
