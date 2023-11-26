package com.apapedia.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.apapedia.order.model.Order;
import com.apapedia.order.model.OrderItem;
import com.apapedia.order.model.OrderItemId;
import com.apapedia.order.repository.CartDb;
import com.apapedia.order.repository.CartItemDb;
import com.apapedia.order.repository.OrderDb;
import com.apapedia.order.repository.OrderItemDb;

import jakarta.transaction.Transactional;

@Service
@Transactional
@ComponentScan(basePackages = "com.apapedia")
public class OrderServiceimpl implements OrderService{

    @Autowired
    CartDb cartDb;

    @Autowired
    CartItemDb cartItemDb;

    @Autowired
    OrderDb orderDb;

    @Autowired
    OrderItemDb orderItemDb;

    @Override
    public Order createOrder(Order order) {
        List<OrderItem> listOrderItem = new ArrayList<>();

        if (order.getListOrderItem() != null) {
            listOrderItem = order.getListOrderItem();
            order.setListOrderItem(new ArrayList<>());
        }

        var orderTersimpan = orderDb.save(order);

        for (OrderItem orderItem : listOrderItem){
            createOrderItem(orderItem, orderTersimpan.getIdOrder());
        }

        


        return orderTersimpan;



    }

    @Override
    public void createOrderItem(OrderItem orderItem, UUID orderId) {
       
        // throw new UnsupportedOperationException("Unimplemented method 'createOrderItem'");

         

                

                OrderItemId orderItemId = new OrderItemId(orderId,orderItem.getProductId());

                var order = orderDb.findByIdOrder(orderId);
                
                orderItem.setOrder(order);

                orderItem.setOrderItemId(orderItemId);

                orderItemDb.save(orderItem);

                if (order.getListOrderItem() == null || order.getListOrderItem().size() == 0) {
                    order.getListOrderItem().add(orderItem);
                    order.setTotalPrice(orderItem.getProductPrice()*orderItem.getQuantity());
                    orderDb.save(order);
                }
                else {
                    // List<OrderItem> listOrderItem = new ArrayList<>();

                    var totalPrice = order.getTotalPrice();

                    var orderItemPrice = orderItem.getProductPrice() * orderItem.getQuantity();

                    totalPrice += orderItemPrice;

                    order.setTotalPrice(totalPrice);



                    order.getListOrderItem().add(orderItem);

                    orderDb.save(order);
                }

                // return orderTersimpan;
            
        
    }

    @Override
    public List<Order> getOrderByIdCustomer(UUID customerId) {
        
        return orderDb.findByCustomer(customerId);
    }
    
}
