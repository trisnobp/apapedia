package com.apapedia.catalogue.restservice;

import com.apapedia.catalogue.model.Catalogue;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CatalogueRestService {
    public List<Catalogue> retrieveRestAllCatalogue();

    public Optional<Catalogue> getRestCatalogueById(UUID id);
}
