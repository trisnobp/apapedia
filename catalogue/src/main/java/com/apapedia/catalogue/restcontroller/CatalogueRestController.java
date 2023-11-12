package com.apapedia.catalogue.restcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.apapedia.catalogue.DTO.CatalogueMapper;
import com.apapedia.catalogue.DTO.request.CreateCatalogueRequestDTO;
import com.apapedia.catalogue.model.Catalogue;
import com.apapedia.catalogue.restservice.CatalogueRestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/catalogue")
public class CatalogueRestController {

    @Autowired
    private CatalogueRestService catalogueRestService;

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
            catalogueRestService.createRestCatalogue(catalogue);
            return catalogue;
        }
    }

    @GetMapping(value = "/view-all")
    private List<Catalogue> retrieveAllCatalogue() {
        return catalogueRestService.retrieveRestAllCatalogue();
    }
    
}
