package com.apapedia.order.DTO.request;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.apapedia.order.model.OrderItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
