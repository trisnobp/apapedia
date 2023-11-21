package com.apapedia.catalogue.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.apapedia.catalogue.DTO.request.CreateCatalogueRequestDTO;
import com.apapedia.catalogue.model.Catalogue;
import com.apapedia.catalogue.model.Category;
import com.apapedia.catalogue.repository.CatalogueDb;
import com.apapedia.catalogue.repository.CategoryDb;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CatalogueServiceImpl implements CatalogueService{

    @Autowired
    CatalogueDb catalogueDb;

    @Autowired
    CategoryDb categoryDb;

    @Override
    public Catalogue createCatalogue(CreateCatalogueRequestDTO catalogueDTO) {
        Category category = catalogueDTO.getNamaCategory();

        if (category == null || !categoryDb.existsById(category.getId())) {
            throw new RuntimeException("Category not found or invalid");
        }

        Catalogue newCatalogue = new Catalogue();
        newCatalogue.setCategory(category);
        newCatalogue.setPrice(catalogueDTO.getPrice());
        newCatalogue.setProductName(catalogueDTO.getProductName());
        newCatalogue.setProductDesc(catalogueDTO.getProductDesc());
        newCatalogue.setStock(catalogueDTO.getStock());
        newCatalogue.setImage(catalogueDTO.getImage());
        newCatalogue.setName(catalogueDTO.getName());
        
        return catalogueDb.save(newCatalogue);
    }
    
    @Override
    public List<Catalogue> retrieveAllCatalogue() {
        return catalogueDb.findAllByOrderByNameAsc();
    }

    @Override
    public Optional<Catalogue> getCatalogueById(UUID id) {
        return catalogueDb.findById(id);
    }

    @Override
    public void deleteCatalogue(UUID id) {
        Optional<Catalogue> catalogueOptional = catalogueDb.findById(id);
        if (catalogueOptional.isPresent()) {
            catalogueDb.delete(catalogueOptional.get());
        } else {
            throw new EntityNotFoundException("Catalogue with ID " + id + " not found");
        }
    }
    
}
