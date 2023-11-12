package com.apapedia.order.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apapedia.order.model.Cart;
import com.apapedia.order.model.CartItem;

@Repository
public interface CartItemDb extends JpaRepository<CartItem,UUID> {


    CartItem save(CartItem cartItem);

    
} 