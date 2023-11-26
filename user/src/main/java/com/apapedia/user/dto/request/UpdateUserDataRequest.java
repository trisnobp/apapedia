package com.apapedia.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDataRequest {
    private UUID id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String address;
}
