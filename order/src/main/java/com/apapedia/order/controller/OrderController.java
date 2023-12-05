package com.apapedia.order.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.apapedia.order.DTO.OrderMapper;
import com.apapedia.order.DTO.request.OrderDTO;
import com.apapedia.order.model.Cart;
import com.apapedia.order.model.CartItem;
import com.apapedia.order.model.Order;
import com.apapedia.order.service.CartServiceimpl;
import com.apapedia.order.service.OrderServiceimpl;

@Controller
@RestController
@RequestMapping("/orderservice")
public class OrderController {
    

    @Autowired
    CartServiceimpl cartServiceimpl;

    @Autowired
    OrderServiceimpl orderServiceimpl;


    @Autowired
    OrderMapper orderMapper;

    // Membuat Order
    @PostMapping("order/create")
    public Order createOrder(@RequestBody OrderDTO orderDTO,Model model){

        var order = orderMapper.OrderDTOToOrder(orderDTO);

        var orderTerbaru = orderServiceimpl.createOrder(order);

        return orderTerbaru;

    }

    //Mendapatkan Order berdasarkan id customer tertentu
    @GetMapping("order/{idCustomer}")
    public List<Order> getOrder(@PathVariable("idCustomer") UUID idCustomer,Model model) {
        
        
         try {
           return orderServiceimpl.getOrderByIdCustomer(idCustomer);
        } catch (NoSuchElementException e){
            throw new  ResponseStatusException(
            HttpStatus.NOT_FOUND, "id Customer " + idCustomer + " not found");}
    
    }
    

   
}
