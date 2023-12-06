package com.apapedia.frontend.DTO.response;

import lombok.Data;

import java.util.UUID;

@Data
public class CategoryDetailDTO {
    private UUID id;
    private String categoryName;
}
