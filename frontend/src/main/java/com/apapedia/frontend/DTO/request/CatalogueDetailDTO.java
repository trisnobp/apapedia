package com.apapedia.frontend.DTO.request;



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.UUID;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class CatalogueDetailDTO {

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
