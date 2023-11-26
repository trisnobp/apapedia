package com.apapedia.order.dto;

import com.apapedia.order.dto.request.CartItemDTO;
import com.apapedia.order.dto.response.CreateUserCartResponse;
import com.apapedia.order.model.Cart;
import org.mapstruct.Mapper;

import com.apapedia.order.DTO.request.CartItemDTO;
import com.apapedia.order.model.CartItem;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartItem CartItemDTOToCartItem(CartItemDTO cartItemDTO);
    CreateUserCartResponse CartToUserCartResponse(Cart cart);
}
