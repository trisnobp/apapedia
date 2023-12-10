package com.apapedia.order.dto.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateOrderRequestDTO {
    private UUID orderId;
    private Integer status;
}
