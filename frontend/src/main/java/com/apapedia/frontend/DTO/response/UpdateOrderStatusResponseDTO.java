package com.apapedia.frontend.DTO.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateOrderStatusResponseDTO {
    private boolean status;
    private String message;
}
