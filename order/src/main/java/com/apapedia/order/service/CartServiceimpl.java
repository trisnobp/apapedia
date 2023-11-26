package com.apapedia.order.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.apapedia.order.model.Cart;
import com.apapedia.order.model.CartItem;
import com.apapedia.order.repository.CartDb;
import com.apapedia.order.repository.CartItemDb;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class CartServiceimpl implements CartService{

    @Autowired
    CartDb cartDb;


    @Autowired
    CartItemDb cartItemDb;
    @Override
    public Cart createCart(Cart cart) {


        var cartTersimpan = cartDb.save(cart);

        return cartTersimpan;

    }

    @Override
    public CartItem createCartItemBaru(CartItem cartItem) {

        cartItemDb.save(cartItem);

        return cartItem;
    }

    @Override
    public Cart getCartByUserId(UUID userId) {

        return cartDb.findByUserId(userId);
    }

    @Override
    public Cart findByIdCart(UUID idCart) {
        
        return cartDb.findByIdCart(idCart);
    }
    
}
