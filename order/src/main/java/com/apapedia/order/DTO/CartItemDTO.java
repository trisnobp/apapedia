package com.apapedia.order.DTO;

import java.util.UUID;

import com.apapedia.order.model.Cart;

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
public class CartItemDTO {

    // private UUID idCartItem ;
  

    private UUID productId;

    private Cart Cart;

    private Integer quantity ; 
}
