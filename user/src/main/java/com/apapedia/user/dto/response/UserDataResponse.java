package com.apapedia.user.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDataResponse {
    private String name;
    private String username;
    private String email;
    private long balance;
    private String address;
    private LocalDateTime createdAt;
    @JsonInclude
    private LocalDateTime updatedAt;
    private String category;
}
