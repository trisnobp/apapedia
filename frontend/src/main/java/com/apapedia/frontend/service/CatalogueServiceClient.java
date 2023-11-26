package com.apapedia.frontend.service;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.apapedia.frontend.DTO.request.GetCatalogueDetailDTO;

// Gilang : membuat service API untuk berkomunikasi dengan microservice catalog Service
@FeignClient(name = "catalogue", url = "http://localhost:8081/api/catalogue")
public interface CatalogueServiceClient {

    @GetMapping("/{id}")
    ResponseEntity<GetCatalogueDetailDTO> retrieveCatalogue(@PathVariable("id") UUID id);
}
