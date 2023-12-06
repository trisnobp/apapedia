package com.apapedia.order.service;

import java.util.List;
import java.util.UUID;

import com.apapedia.order.dto.request.CreateOrderRequestDTO;
import com.apapedia.order.dto.request.OrderItemDTO;
import com.apapedia.order.dto.response.CreateOrderResponseDTO;
import com.apapedia.order.model.Order;
import com.apapedia.order.model.OrderItem;

public interface OrderService {

    CreateOrderResponseDTO createOrder(CreateOrderRequestDTO createOrderRequestDTO);

    void createOrderItem(OrderItemDTO orderItem , UUID orderId);

    List<Order> getOrderByIdCustomer(UUID customerId);
     
}