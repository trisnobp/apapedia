package com.apapedia.order.DTO;

import org.mapstruct.Mapper;

import com.apapedia.order.DTO.request.CartItemDTO;
import com.apapedia.order.model.CartItem;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartItem cartItemDTOToCartItem(CartItemDTO cartItemDTO);
    
}
