package com.apapedia.order.service;

import java.util.List;
import java.util.UUID;

import com.apapedia.order.model.Order;
import com.apapedia.order.model.OrderItem;

public interface OrderService {

    Order createOrder(Order order);

    void createOrderItem(OrderItem orderItem , UUID orderId);

    List<Order> getOrderByIdCustomer(UUID customerId);
     
}