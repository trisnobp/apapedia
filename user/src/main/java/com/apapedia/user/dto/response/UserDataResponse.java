package com.apapedia.user.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDataResponse {
    private UUID id;
    private String name;
    private String username;
    private String email;
    private long balance;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String role;
    // Can be null
    private String category;
    private UUID cartId;
}
