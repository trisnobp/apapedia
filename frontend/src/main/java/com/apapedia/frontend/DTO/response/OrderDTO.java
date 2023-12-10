package com.apapedia.frontend.DTO.response;

import com.apapedia.frontend.DTO.request.OrderItemDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    private UUID idOrder ;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer status;
    private long totalPrice;
    private UUID customer;
    private String customerName;
    private UUID seller;
    private boolean isDeleted;
    private List<OrderItemDTO> listOrderItem;

}
