package com.apapedia.order.dto.request;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.apapedia.order.model.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OrderDTO {

    
    
    
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Integer status ;

    
    private Integer totalPrice ;

    private UUID seller;

    private UUID customer;

    private List<OrderItem> listOrderItem;
}
