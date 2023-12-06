package com.apapedia.catalogue.DTO.response;

import lombok.Data;

import java.util.UUID;

@Data
public class CategoryDetailDTO {
    private UUID id;
    private String categoryName;
}
