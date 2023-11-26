package com.apapedia.order.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apapedia.order.model.Cart;
import com.apapedia.order.model.Order;
import java.util.List;



@Repository
public interface OrderDb extends JpaRepository<Order,UUID> {

    Order findByIdOrder(UUID idOrder);

    @Query("SELECT o FROM Order o WHERE o.customer = :customer order by o.customer")
    List<Order> findByCustomer(@Param("customer") UUID customer);
    
} 