package com.apapedia.catalogue.controller;

import java.util.List;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.apapedia.catalogue.DTO.CatalogueMapper;
import com.apapedia.catalogue.DTO.request.CreateCatalogueRequestDTO;
import com.apapedia.catalogue.model.Catalogue;
import com.apapedia.catalogue.model.Category;
import com.apapedia.catalogue.service.CatalogueService;
import com.apapedia.catalogue.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/catalogue")
public class CatalogueController {

    @Autowired
    private CatalogueService catalogueService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CatalogueMapper catalogueMapper;

    @PostMapping(value = "/create")
    public ResponseEntity<Catalogue> addCatalogue(@Valid @RequestBody CreateCatalogueRequestDTO catalogueDTO, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        }

        Catalogue createdCatalogue = catalogueService.createCatalogue(catalogueDTO);
        return new ResponseEntity<>(createdCatalogue, HttpStatus.CREATED);
    }

    @GetMapping(value = "/view-all")
    private List<Catalogue> allCatalogue() {
        return catalogueService.retrieveAllCatalogue(); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Catalogue> retrieveCatalogue(@PathVariable UUID id) {
        return catalogueService.getCatalogueById(id)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Catalogue with ID " + id + " not found"
            ));
    }

    @GetMapping("/{id}/delete")
    public ResponseEntity<?> deleteCatalogue(@PathVariable("id") UUID id) {
        catalogueService.deleteCatalogue(id);
        return ResponseEntity.ok().build(); // Return a 200 OK to indicate successful soft delete
    }

    @GetMapping("/search")
    public ResponseEntity<List<Catalogue>> getCatalogsByName(@RequestParam String productName) {
        List<Catalogue> catalogs = catalogueService.findByProductName(productName);
        return ResponseEntity.ok(catalogs);
    }

    @GetMapping("/category/viewall")
    public ResponseEntity<List<Category>> AllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<Catalogue>> getCataloguesBySellerId(@PathVariable UUID sellerId) {
        List<Catalogue> catalogues = catalogueService.getCataloguesBySellerId(sellerId);
        return ResponseEntity.ok(catalogues);
    }
   
}
