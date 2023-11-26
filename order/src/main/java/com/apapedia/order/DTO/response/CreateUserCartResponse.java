package com.apapedia.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserCartResponse {

    private UUID idCart;
    private UUID cartId;
    private long totalPrice;
}
