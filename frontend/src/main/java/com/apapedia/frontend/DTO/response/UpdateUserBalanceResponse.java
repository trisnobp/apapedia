package com.apapedia.frontend.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserBalanceResponse {
    private boolean status;
    private String message;
    private long balance;
}
