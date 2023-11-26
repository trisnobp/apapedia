package com.apapedia.order.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apapedia.order.model.Cart;
import com.apapedia.order.model.OrderItem;
import com.apapedia.order.model.OrderItemId;

@Repository
public interface OrderItemDb extends JpaRepository<OrderItem,OrderItemId> {


    OrderItem save(OrderItem orderItem);
} 