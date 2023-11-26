package com.apapedia.order.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.apapedia.order.dto.CartMapper;
import com.apapedia.order.dto.response.CreateUserCartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apapedia.order.dto.request.CartItemDTO;
import com.apapedia.order.model.Cart;
import com.apapedia.order.model.CartItem;
import com.apapedia.order.service.CartServiceimpl;
import com.apapedia.order.service.OrderServiceimpl;

@Controller
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartServiceimpl cartServiceimpl;

    @Autowired
    OrderServiceimpl orderServiceimpl;

    @Autowired
    CartMapper cartMapper;

    @PostMapping("/create/{userId}")
    public ResponseEntity<CreateUserCartResponse> createCart(@PathVariable("userId") UUID userId){
        
        Cart cartBaru = new Cart();
        cartBaru.setUserId(userId);
        cartBaru.setTotalPrice(0);

        var savedCart = cartServiceimpl.createCart(cartBaru);
        var savedCartResponse = cartMapper.CartToUserCartResponse(savedCart);
        return ResponseEntity.ok(savedCartResponse);
    }

    @GetMapping("/cartItem/{userId}")
    public ResponseEntity<List<CartItem>> getCartItem(@PathVariable("userId") UUID userId){

        var listCart = cartServiceimpl.getCartByUserId(userId);

        return ResponseEntity.ok(cart.getListCartItem());
    }

    @PostMapping("/cartItem/create")
    public CartItem addCartItem(@RequestBody CartItemDTO cartItemDTO ) {

        CartItem cartItem = cartMapper.cartItemDTOToCartItem(cartItemDTO);

        Cart cart = cartServiceimpl.findByIdCart(cartItemDTO.getIdCart());

        cartItem.setCart(cart);

        cart.getListCartItem().add(cartItem);

        cartServiceimpl.createCart(cart);
       
        cartServiceimpl.createCartItemBaru(cartItem);
        
        return cartItem;

    }

}
