package com.apapedia.order.service;

import java.util.List;
import java.util.UUID;

import com.apapedia.order.model.Cart;
import com.apapedia.order.model.CartItem;

public interface CartService {
    
    Cart createCart(Cart cart);
    CartItem createCartItemBaru(CartItem cartItem);
    Cart getCartByUserId(UUID userId);

}
