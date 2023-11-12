package com.apapedia.catalogue.service;

import com.apapedia.catalogue.model.Catalogue;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CatalogueService {
    public void createCatalogue(Catalogue catalogue);

    public List<Catalogue> retrieveAllCatalogue();

    public Optional<Catalogue> getCatalogueById(UUID id);
}
