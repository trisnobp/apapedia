package com.apapedia.catalogue.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.apapedia.catalogue.DTO.request.UpdateCatalogueRequestDTO;
import com.apapedia.catalogue.DTO.response.ResponseCatalogueDTO;
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

    public Optional<Category.CategoryName> findCategoryByName(String display) {
        return Arrays.stream(Category.CategoryName.values())
                .filter(category -> category.toString().equals(display))
                .findFirst();
    }

    @Override
    public Catalogue createCatalogue(CreateCatalogueRequestDTO catalogueDTO) {
        var categoryName = findCategoryByName(catalogueDTO.getCategory()); // Get the category name
        var newCategory = categoryDb.findByNamaCategory(categoryName.get()).get();

        Catalogue newCatalogue = new Catalogue();
        newCatalogue.setCategory(newCategory);
        newCatalogue.setPrice(catalogueDTO.getPrice()); 
        newCatalogue.setSellerId(catalogueDTO.getSellerId());
        newCatalogue.setProductName(catalogueDTO.getProductName());
        newCatalogue.setProductDesc(catalogueDTO.getProductDesc());
        newCatalogue.setStock(catalogueDTO.getStock());
        newCatalogue.setImage(catalogueDTO.getImage());
        
        return catalogueDb.save(newCatalogue);
    }

    @Override
    public Catalogue updateCatalogue(UpdateCatalogueRequestDTO catalogueDTO) {
        // Get catalogue by Id
        var catalogueById = catalogueDb.findById(catalogueDTO.getId()).get();

        // Get category
        var categoryName = findCategoryByName(catalogueDTO.getDisplayCategory()); // Get the category name
        var newCategory = categoryDb.findByNamaCategory(categoryName.get()).get();

        // Update catalogue
        catalogueById.setCategory(newCategory);
        catalogueById.setPrice(catalogueDTO.getPrice());
        catalogueById.setImage(catalogueDTO.getImage());
        catalogueById.setStock(catalogueDTO.getStock());
        catalogueById.setProductDesc(catalogueDTO.getProductDesc());
        catalogueById.setProductName(catalogueDTO.getProductName());
        return catalogueDb.save(catalogueById);
    }

    @Override
    public List<Catalogue> retrieveAllCatalogue() {
        return catalogueDb.findByStockNotOrderByProductNameAsc(0);
    } // Get All Products that has > 0 stock

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

    @Override
    public List<Catalogue> findByProductName(String productName) {
        return catalogueDb.findByProductNameContaining(productName);
    }

    @Override
    public List<Catalogue> getCataloguesBySellerId(UUID sellerId) {
        return catalogueDb.findBySellerId(sellerId);
    }
}
