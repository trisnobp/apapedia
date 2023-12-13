package com.apapedia.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

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
