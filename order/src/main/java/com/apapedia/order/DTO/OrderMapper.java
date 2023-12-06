package com.apapedia.order.dto;

import org.mapstruct.Mapper;

import com.apapedia.order.dto.request.CreateOrderRequestDTO;
import com.apapedia.order.dto.request.OrderItemDTO;
import com.apapedia.order.model.Order;
import com.apapedia.order.model.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderItem OrderItemDTOToOrderItem(OrderItemDTO orderItemDTO);

    Order OrderDTOToOrder(CreateOrderRequestDTO createOrderRequestDTO);
}
