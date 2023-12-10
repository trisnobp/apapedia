package com.apapedia.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import com.apapedia.order.dto.OrderMapper;
import com.apapedia.order.dto.request.CreateOrderRequestDTO;
import com.apapedia.order.dto.request.UpdateOrderRequestDTO;
import com.apapedia.order.dto.response.CreateOrderResponseDTO;
import com.apapedia.order.dto.response.UpdateOrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.apapedia.order.model.Order;
import com.apapedia.order.service.CartServiceimpl;
import com.apapedia.order.service.OrderServiceimpl;

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
    public ResponseEntity<CreateOrderResponseDTO> createOrder(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateOrderRequestDTO createOrderRequestDTO){
        var orderTerbaru = orderServiceimpl.createOrder(token.substring(7), createOrderRequestDTO);
        return ResponseEntity.ok(orderTerbaru);
    }

    //Mendapatkan Order berdasarkan id customer tertentu
    @GetMapping("/customer/{idCustomer}")
    public List<Order> getOrderByCustomerId(@PathVariable("idCustomer") UUID idCustomer) {
        
         try {
           return orderServiceimpl.getOrderByIdCustomer(idCustomer);
        } catch (NoSuchElementException e){
            throw new  ResponseStatusException(
            HttpStatus.NOT_FOUND, "id Customer " + idCustomer + " not found");}
    }

    @GetMapping("/seller/{idSeller}")
    public List<Order> getOrderBySellerId(@PathVariable("idSeller") UUID idSeller) {

        try {
            return orderServiceimpl.getOrderByIdSeller(idSeller);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "id Seller " + idSeller + " not found");
        }
    }

    @PutMapping("/{idOrder}/update")
    public ResponseEntity<UpdateOrderResponseDTO> updateOrderStatus(
            @RequestHeader("Authorization") String token,
            @RequestBody UpdateOrderRequestDTO updateOrderRequestDTO
    ) {
        var updatedOrderResponse = orderServiceimpl.updateOrderStatus(token.substring(7), updateOrderRequestDTO);
        return ResponseEntity.ok(updatedOrderResponse);
    }

    @DeleteMapping("/{idOrder}/delete")
    public ResponseEntity<String> softDeleteOrderById(
            @PathVariable("idOrder") UUID orderId
    ) {
        orderServiceimpl.softDeleteOrderByOrderId(orderId);
        return ResponseEntity.ok("Order berhasil dihapus.");
    }

    @GetMapping("/{idSeller}/top-products")
    public ResponseEntity<HashMap<String, Long>> getTopFiveProductsThisMonth(
            @PathVariable("idSeller") UUID id
    ) {
        return ResponseEntity.ok(orderServiceimpl.getTopFiveProductsThisMonth(id));
    }
}
