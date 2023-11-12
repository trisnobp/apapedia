package com.apapedia.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserCartResponse {
    private UUID id;
    private UUID userId;
    private int totalPrice;
}
