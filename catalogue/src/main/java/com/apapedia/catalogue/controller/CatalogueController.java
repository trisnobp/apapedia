package com.apapedia.catalogue.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import java.util.UUID;

import com.apapedia.catalogue.DTO.request.UpdateCatalogueRequestDTO;
import com.apapedia.catalogue.DTO.response.CategoryDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.apapedia.catalogue.DTO.CatalogueMapper;
import com.apapedia.catalogue.DTO.request.CreateCatalogueRequestDTO;
import com.apapedia.catalogue.DTO.response.ResponseCatalogueDTO;
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
    public ResponseEntity<ResponseCatalogueDTO> addCatalogue(@Valid @RequestBody CreateCatalogueRequestDTO catalogueDTO, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        }

        Catalogue createdCatalogue = catalogueService.createCatalogue(catalogueDTO);
        var responseCatalogue = catalogueMapper.catalogueTOResponseCatalogueDTO(createdCatalogue);
        return new ResponseEntity<>(responseCatalogue, HttpStatus.CREATED);
    }

    @GetMapping(value = "/view-all")
    private ResponseEntity<List<Catalogue>> allCatalogue() {
        return ResponseEntity.ok(catalogueService.retrieveAllCatalogue());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCatalogueDTO> retrieveCatalogue(@PathVariable UUID id) {
        var catalogue =  catalogueService.getCatalogueById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Catalogue with ID " + id + " not found"
                ));
        var responseDTO = catalogueMapper.catalogueTOResponseCatalogueDTO(catalogue.getBody());

        responseDTO.setDisplayCategory(catalogue.getBody().getCategory().getNamaCategory().toString());

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}/delete")
    public ResponseEntity<?> deleteCatalogue(@PathVariable("id") UUID id) {
        catalogueService.deleteCatalogue(id);
        return ResponseEntity.ok().build(); // Return a 200 OK to indicate successful soft delete
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<ResponseCatalogueDTO> updateCatalogue(
            @PathVariable("id") UUID id,
            @RequestBody UpdateCatalogueRequestDTO catalogueDTO
    ) {
        var updatedCatalogue = catalogueService.updateCatalogue(catalogueDTO);
        var response = catalogueMapper.catalogueTOResponseCatalogueDTO(updatedCatalogue);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ResponseCatalogueDTO>> getCatalogsByFilter(
            @RequestParam(name = "productName", required = false) String nama
    ) {
        List<ResponseCatalogueDTO> listOfResponses = new ArrayList<>();
        List<Catalogue> catalogs = catalogueService.findByProductName(nama);
        for (var catalog: catalogs) {
            listOfResponses.add(catalogueMapper.catalogueTOResponseCatalogueDTO(catalog));
        }
        return ResponseEntity.ok(listOfResponses);
    }

    @GetMapping("/searchByPrice")
    public ResponseEntity<List<ResponseCatalogueDTO>> getCatalogsByPriceRange(
            @RequestParam(name = "startPrice", required = false) BigDecimal startPrice,
            @RequestParam(name = "endPrice", required = false) BigDecimal endPrice
    ) {
        List<ResponseCatalogueDTO> listOfResponses = new ArrayList<>();
        List<Catalogue> catalogs = catalogueService.findByPriceRange(startPrice, endPrice);
        for (var catalog: catalogs) {
            listOfResponses.add(catalogueMapper.catalogueTOResponseCatalogueDTO(catalog));
        }
        return ResponseEntity.ok(listOfResponses);
    }

    @GetMapping("/sortProducts")
    public ResponseEntity<List<ResponseCatalogueDTO>> getSortedCatalog(
            @RequestParam(name = "sortBy", required = false) String sortBy,
            @RequestParam(name = "order", required = false) String order
    ) {
        List<ResponseCatalogueDTO> listOfResponses = new ArrayList<>();
        List<Catalogue> catalogs = catalogueService.findBySorted(sortBy, order);
        for (var catalog: catalogs) {
            listOfResponses.add(catalogueMapper.catalogueTOResponseCatalogueDTO(catalog));
        }
        return ResponseEntity.ok(listOfResponses);
    }

    @GetMapping("/category/viewall")
    public ResponseEntity<List<CategoryDetailDTO>> getAllCategories() {
        List<CategoryDetailDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<Catalogue>> getCataloguesBySellerId(@PathVariable UUID sellerId) {
        List<Catalogue> catalogues = catalogueService.getCataloguesBySellerId(sellerId);
        return ResponseEntity.ok(catalogues);
    }

}
