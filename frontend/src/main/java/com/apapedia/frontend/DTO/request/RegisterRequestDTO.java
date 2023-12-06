package com.apapedia.frontend.DTO.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    private String name;
    private String username;
    private String email;
    private String password;
    private String address;
    private String category; // If null, then we can identify that this request comes from customer
}
