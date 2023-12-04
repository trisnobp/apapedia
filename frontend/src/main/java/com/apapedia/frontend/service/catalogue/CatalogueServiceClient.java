package com.apapedia.frontend.service.catalogue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.apapedia.frontend.DTO.request.CreateCatalogueRequestDTO;
import com.apapedia.frontend.DTO.response.CategoryDetailDTO;
import com.apapedia.frontend.DTO.response.CreateCatalogueResponseDTO;
import com.apapedia.frontend.DTO.response.UpdateCatalogueResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.apapedia.frontend.DTO.request.CatalogueDetailDTO;

// Gilang : membuat service API untuk berkomunikasi dengan microservice catalog Service
@FeignClient(name = "catalogue", url = "http://localhost:8081/api/catalogue")
public interface CatalogueServiceClient {

    @GetMapping("/{id}")
    ResponseEntity<CatalogueDetailDTO> retrieveCatalogue(
            @RequestHeader("Authorization") String token,
            @PathVariable("id") UUID id
    );

    @GetMapping("/seller/{sellerId}")
    ResponseEntity<List<CatalogueDetailDTO>> retrieveSellerCatalogue(
            @RequestHeader("Authorization") String token,
            @PathVariable("sellerId") UUID sellerId
    );

    @GetMapping("/view-all")
    ResponseEntity<List<CatalogueDetailDTO>> retrieveAllCatalogue();

    @GetMapping("/category/viewall")
    ResponseEntity<List<CategoryDetailDTO>> getAllCategories(
            @RequestHeader("Authorization") String token
    );

    @PostMapping("/create")
    ResponseEntity<CreateCatalogueResponseDTO> addCatalogue(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateCatalogueRequestDTO catalogueDTO
    );

    @PutMapping("/{id}/update")
    public ResponseEntity<UpdateCatalogueResponseDTO> updateCatalogue(
            @RequestHeader("Authorization") String token,
            @PathVariable("id") UUID id,
            @RequestBody CatalogueDetailDTO catalogueDTO
    );

    @GetMapping("/search")
    public ResponseEntity<List<CatalogueDetailDTO>> getCatalogsByName(
            @RequestParam(name = "productName", required = false) String nama
    );

    @GetMapping("/searchByPrice")
    public ResponseEntity<List<CatalogueDetailDTO>> getCatalogsByPriceRange(
            @RequestParam(name = "startPrice", required = false) BigDecimal startPrice,
            @RequestParam(name = "endPrice", required = false) BigDecimal endPrice
    );

    @GetMapping("/sortProducts")
    public ResponseEntity<List<CatalogueDetailDTO>> getSortedCatalog(
            @RequestParam(name = "sortBy", required = false) String sortBy,
            @RequestParam(name = "order", required = false) String order
    );
}
