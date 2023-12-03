package com.apapedia.frontend.DTO.response;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CreateCatalogueResponseDTO {
    private UUID id;
    private String image;
    private MultipartFile file;
    private BigDecimal price;
    private String productName;
    private String productDesc;
    private UUID sellerId;
    private int stock;
    private String displayCategory;
}
