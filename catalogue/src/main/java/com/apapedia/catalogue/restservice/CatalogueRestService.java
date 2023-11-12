package com.apapedia.catalogue.restservice;

import com.apapedia.catalogue.model.Catalogue;
import java.util.List;

public interface CatalogueRestService {
    public void createRestCatalogue(Catalogue catalogue);

    public List<Catalogue> retrieveRestAllCatalogue();
}
