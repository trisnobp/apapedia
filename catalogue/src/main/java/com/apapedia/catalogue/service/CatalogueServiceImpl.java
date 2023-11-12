package com.apapedia.catalogue.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
    @Override
    public List<Catalogue> retrieveAllCatalogue() {
        return catalogueDb.findAllByOrderByJudulAsc();
    }

    @Override
    public Optional<Catalogue> getCatalogueById(UUID id) {
        return catalogueDb.findById(id);
    }

    @Override
    public void deleteCatalogue(Catalogue catalogue) {
        catalogueDb.delete(catalogue);
    }
}
