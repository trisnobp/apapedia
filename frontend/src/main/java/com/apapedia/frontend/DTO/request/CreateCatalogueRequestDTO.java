package com.apapedia.frontend.DTO.request;

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
public class CreateCatalogueRequestDTO {
    private UUID sellerId;
    private BigDecimal price;
    private String productName;
    private String productDesc;
    private String category;
    private int stock;
    private MultipartFile file;
    private String image;
}
