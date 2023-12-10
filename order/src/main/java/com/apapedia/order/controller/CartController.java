package com.apapedia.order.controller;

import java.util.List;
import java.util.UUID;

import com.apapedia.order.dto.CartMapper;
import com.apapedia.order.dto.response.CreateUserCartResponseDTO;
import com.apapedia.order.service.CartService;
import com.apapedia.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.apapedia.order.dto.request.CartItemDTO;
import com.apapedia.order.model.Cart;
import com.apapedia.order.model.CartItem;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService cartServiceimpl;

    @Autowired
    OrderService orderServiceimpl;

    @Autowired
    CartMapper cartMapper;

    @PostMapping("/create/{userId}")
    public ResponseEntity<CreateUserCartResponseDTO> createCart(@PathVariable("userId") UUID userId){
        Cart cartBaru = new Cart();
        cartBaru.setUserId(userId);
        cartBaru.setTotalPrice(0);

        var savedCart = cartServiceimpl.createCart(cartBaru);
        var savedCartResponse = cartMapper.CartToUserCartResponse(savedCart);
        return ResponseEntity.ok(savedCartResponse);
    }

    @GetMapping("/{userId}/cartItem")
    public ResponseEntity<List<CartItem>> getCartItem(@PathVariable("userId") UUID userId){
        var listCart = cartServiceimpl.getCartByUserId(userId);
        return ResponseEntity.ok(listCart.getListCartItem());
    }

    @PostMapping("/{userId}/cartItem/create")
    public CartItem addCartItem(@RequestBody CartItemDTO cartItemDTO) {
        CartItem cartItem = cartMapper.CartItemDTOToCartItem(cartItemDTO);
        Cart cart = cartServiceimpl.findByIdCart(cartItemDTO.getIdCart());
        cartItem.setCart(cart);
        cart.getListCartItem().add(cartItem);
        cartServiceimpl.createCart(cart);
        cartServiceimpl.createCartItemBaru(cartItem);

        return cartItem;
    }

    @PutMapping("/{userId}/cartItem/{cartItemId}/update")
    public ResponseEntity<CartItem> updateCartItem(
            @RequestBody CartItemDTO cartItemDTO
    ) {
        var updatedCartItem = cartServiceimpl.updateCartItem(cartItemDTO);
        return ResponseEntity.ok(updatedCartItem);
    }

    @DeleteMapping("/{userId}/cartItem/{cartItemId}/delete")
    public ResponseEntity<?> deleteCartItem(
            @PathVariable("cartItemId") UUID cartItemId
    ) {
        try {
            cartServiceimpl.deleteCartItem(cartItemId);
            return ResponseEntity.ok("Cart item berhasil dihapus.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }



}
