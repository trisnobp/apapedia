package com.apapedia.catalogue.service;

import com.apapedia.catalogue.DTO.request.CreateCatalogueRequestDTO;
import com.apapedia.catalogue.DTO.request.UpdateCatalogueRequestDTO;
import com.apapedia.catalogue.DTO.response.ResponseCatalogueDTO;
import com.apapedia.catalogue.model.Catalogue;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CatalogueService {
    public Catalogue createCatalogue(CreateCatalogueRequestDTO catalogueDTO);

    public List<Catalogue> retrieveAllCatalogue();

    public Optional<Catalogue> getCatalogueById(UUID id);

    public void deleteCatalogue(UUID id);

    public List<Catalogue> findByProductName(String productName);

    public List<Catalogue> getCataloguesBySellerId(UUID sellerId);
    public Catalogue updateCatalogue(UpdateCatalogueRequestDTO catalogueDTO);
}
