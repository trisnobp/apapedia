package com.apapedia.catalogue.DTO.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateCatalogueRequestDTO {
    private UUID id;
    private UUID sellerId;
    private String image;
    private MultipartFile file;
    private BigDecimal price;
    private String productName;
    private String productDesc;
    private int stock;
    private String displayCategory;
}
