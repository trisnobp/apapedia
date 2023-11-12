package com.apapedia.order.controller;

import com.apapedia.order.dto.request.CreateCartRequest;
import com.apapedia.order.model.Cart;
import com.apapedia.order.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/create")
    public ResponseEntity<Cart> addCart(
            @RequestBody CreateCartRequest createCartRequest
    ) {
        var newCart = cartService.createCart(createCartRequest);
        return ResponseEntity.ok(newCart);
    }
}
