package com.apapedia.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.apapedia.order.dto.request.CreateOrderRequestDTO;
import com.apapedia.order.dto.request.OrderItemDTO;
import com.apapedia.order.dto.request.UpdateOrderRequestDTO;
import com.apapedia.order.dto.response.CreateOrderResponseDTO;
import com.apapedia.order.dto.response.UpdateOrderResponseDTO;
import com.apapedia.order.model.Order;
import com.apapedia.order.model.OrderItem;

public interface OrderService {
    CreateOrderResponseDTO createOrder(String token, CreateOrderRequestDTO createOrderRequestDTO);
    void createOrderItem(OrderItemDTO orderItem , UUID orderId);
    List<Order> getOrderByIdCustomer(UUID customerId);
    UpdateOrderResponseDTO updateOrderStatus(String token, UpdateOrderRequestDTO requestDTO);
    HashMap<String, Long> getTopFiveProductsThisMonth(UUID sellerId);
    List<Order> getOrderByIdSeller(UUID sellerId);
    void softDeleteOrderByOrderId(UUID orderId);




}