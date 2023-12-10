package com.apapedia.frontend.DTO.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemDTO {
    private UUID orderId; // the value is null when whenever we create an order
    private UUID productId;
    private Integer quantity;
    private String productName;
    private Long productPrice;
}
