package com.apapedia.order.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateOrderResponseDTO {
    private boolean status;
    private String message;
    private UUID orderId;
}
