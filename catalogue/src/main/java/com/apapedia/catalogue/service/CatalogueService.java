package com.apapedia.catalogue.service;

import com.apapedia.catalogue.model.Catalogue;
import java.util.List;

public interface CatalogueService {
    public void createCatalogue(Catalogue catalogue);

    public List<Catalogue> retrieveAllCatalogue();
}
