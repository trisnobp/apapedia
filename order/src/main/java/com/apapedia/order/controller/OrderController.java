package com.apapedia.order.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import com.apapedia.order.dto.OrderMapper;
import com.apapedia.order.dto.request.CreateOrderRequestDTO;
import com.apapedia.order.dto.response.CreateOrderResponseDTO;
import com.apapedia.order.dto.response.ResponseCatalogueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.apapedia.order.model.Order;
import com.apapedia.order.service.CartServiceimpl;
import com.apapedia.order.service.OrderServiceimpl;

@Controller
@RestController
@RequestMapping("/api/order")
public class OrderController {
    

    @Autowired
    CartServiceimpl cartServiceimpl;

    @Autowired
    OrderServiceimpl orderServiceimpl;


    @Autowired
    OrderMapper orderMapper;

    // Membuat Order
    @PostMapping("/create")
    public ResponseEntity<CreateOrderResponseDTO> createOrder(@RequestBody CreateOrderRequestDTO createOrderRequestDTO){
        var orderTerbaru = orderServiceimpl.createOrder(createOrderRequestDTO);
        return ResponseEntity.ok(orderTerbaru);

    }

    //Mendapatkan Order berdasarkan id customer tertentu
    @GetMapping("/{idCustomer}")
    public List<Order> getOrderByCustomerId(@PathVariable("idCustomer") UUID idCustomer) {
        
         try {
           return orderServiceimpl.getOrderByIdCustomer(idCustomer);
        } catch (NoSuchElementException e){
            throw new  ResponseStatusException(
            HttpStatus.NOT_FOUND, "id Customer " + idCustomer + " not found");}
    }

    @GetMapping("/{idSeller}")
    public List<Order> getOrderBySellerId(@PathVariable("idSeller") UUID idSeller) {

        try {
            return orderServiceimpl.getOrderByIdCustomer(idSeller);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "id Customer " + idSeller + " not found");
        }
    }
    

   
}
