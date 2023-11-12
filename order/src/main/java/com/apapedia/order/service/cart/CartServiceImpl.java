package com.apapedia.order.service.cart;

import com.apapedia.order.dto.request.CreateCartRequest;
import com.apapedia.order.model.Cart;
import com.apapedia.order.repository.CartDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    CartDb cartDb;

    @Override
    public Cart createCart(CreateCartRequest createCartRequest) {
        var newCart = Cart.builder().userId(createCartRequest.getUserId()).build();
        cartDb.save(newCart);
        return newCart;
    }
}
