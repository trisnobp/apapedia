package com.apapedia.frontend.DTO.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateOrderStatusRequestDTO {
    private UUID orderId;
    private Integer status;
}
