package com.apapedia.frontend.DTO.request;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateOrderRequestDTO {
    private UUID seller;
    private UUID customer;
    private List<OrderItemDTO> listOrderItem;
}

