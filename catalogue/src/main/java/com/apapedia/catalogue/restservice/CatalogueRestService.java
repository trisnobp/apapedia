package com.apapedia.catalogue.restservice;

import com.apapedia.catalogue.model.Catalogue;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CatalogueRestService {
    public void createRestCatalogue(Catalogue catalogue);

    public List<Catalogue> retrieveRestAllCatalogue();

    public Optional<Catalogue> getRestCatalogueById(UUID id);

    public void deleteCatalogue(Catalogue catalogue);
}
