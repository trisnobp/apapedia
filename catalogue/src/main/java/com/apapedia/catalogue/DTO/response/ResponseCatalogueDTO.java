package com.apapedia.catalogue.DTO.response;

import java.math.BigDecimal;
import java.util.UUID;

import com.apapedia.catalogue.model.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data 
// Gilang : DTO Catalogue untuk response dari request microservice lain
public class ResponseCatalogueDTO {
    private UUID id;
    private String image;
    private BigDecimal price;
    private String productName;
    private String productDesc;
    private UUID sellerId;
    private int stock;
    private String displayCategory;
}
