package com.apapedia.frontend.DTO.request;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetCatalogueDetailDTO {

    private UUID id;

    private String name;

    private String image;

    private BigDecimal price;

    private String productName;

    private String productDesc;

    private int stock;

    private String displayCategory;
}
