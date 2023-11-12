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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.apapedia.catalogue.DTO.CatalogueMapper;
import com.apapedia.catalogue.DTO.request.CreateCatalogueRequestDTO;
import com.apapedia.catalogue.model.Catalogue;
import com.apapedia.catalogue.service.CatalogueService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/catalogue")
public class CatalogueController {

    @Autowired
    private CatalogueService catalogueService;

    @Autowired
    private CatalogueMapper catalogueMapper;

    @PostMapping(value = "/create")
    public Catalogue addCatalogue(@Valid @RequestBody CreateCatalogueRequestDTO catalogueDTO, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
                );
        } else{
            var catalogue = catalogueMapper.createCatalogueRequestDTOToCatalogue(catalogueDTO);
            catalogueService.createCatalogue(catalogue);
            return catalogue;
        }
    }

    @GetMapping(value = "/view-all")
    private List<Catalogue> retrieveAllCatalogue() {
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
}
