package com.apapedia.order.DTO;

import org.mapstruct.Mapper;

import com.apapedia.order.DTO.request.CartItemDTO;
import com.apapedia.order.DTO.request.OrderDTO;
import com.apapedia.order.DTO.request.OrderItemDTO;
import com.apapedia.order.model.CartItem;
import com.apapedia.order.model.Order;
import com.apapedia.order.model.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderItem OrderItemDTOToOrderItem(OrderItemDTO orderItemDTO);

    Order OrderDTOToOrder(OrderDTO orderDTO);
}
