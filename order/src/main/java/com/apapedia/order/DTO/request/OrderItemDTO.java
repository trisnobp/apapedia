package com.apapedia.order.DTO.request;

import java.util.UUID;

import com.apapedia.order.model.Cart;
import com.apapedia.order.model.Order;
import com.apapedia.order.model.OrderItemId;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class OrderItemDTO {
    private OrderItemId orderItemId ;
    private UUID productId;



    private Order order;

    private Integer quantity ;

    private Integer productName;


    private Integer productPrice;

}