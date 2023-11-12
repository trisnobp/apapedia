package com.apapedia.order.service.cart;

import com.apapedia.order.dto.request.CreateCartRequest;
import com.apapedia.order.model.Cart;

import java.util.UUID;

public interface CartService {
    Cart createCart(CreateCartRequest createCartRequest);
}
