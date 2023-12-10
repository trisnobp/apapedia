package com.apapedia.order.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateOrderResponseDTO {
    private boolean status;
    private String message;
}
