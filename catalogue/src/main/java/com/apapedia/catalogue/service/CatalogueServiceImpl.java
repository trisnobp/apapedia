package com.apapedia.catalogue.service;

import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.validator.constraints.UUID;
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

    @Override
    public void deleteCatalogue(Catalogue catalogue) {
        catalogueDb.delete(catalogue);
    }

    @Override
    public Catalogue getCatalogueById(UUID id){
        for (Catalogue catalogue : getAllCatalogue()){
            if (catalogue.getId().equals(id)){
                return catalogue;
            }
        }
        return null;
    }
    
}
