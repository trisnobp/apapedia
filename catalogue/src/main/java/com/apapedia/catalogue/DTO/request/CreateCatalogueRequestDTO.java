package com.apapedia.catalogue.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

import com.apapedia.catalogue.model.Category;

@AllArgsConstructor
@NoArgsConstructor
@Data 
public class CreateCatalogueRequestDTO {

    private String name;

    private String image;

    private UUID sellerId;

    private BigDecimal price;

    private String productName;

    private String productDesc;

    private UUID sellerId;

    private int stock;

    private Category category;
}
