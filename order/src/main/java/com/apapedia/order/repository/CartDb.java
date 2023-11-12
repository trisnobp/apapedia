package com.apapedia.order.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apapedia.order.model.Cart;

@Repository
public interface CartDb extends JpaRepository<Cart, UUID> {

    Cart save(Cart cart);

    Cart getCartByUserId(UUID idCustomer);



    
} 

