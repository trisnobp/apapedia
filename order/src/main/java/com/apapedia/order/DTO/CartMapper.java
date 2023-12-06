package com.apapedia.order.dto;

import com.apapedia.order.dto.request.CartItemDTO;
import com.apapedia.order.dto.response.CreateUserCartResponseDTO;
import com.apapedia.order.model.Cart;
import org.mapstruct.Mapper;
import com.apapedia.order.model.CartItem;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartItem CartItemDTOToCartItem(CartItemDTO cartItemDTO);
    CreateUserCartResponseDTO CartToUserCartResponse(Cart cart);
}
