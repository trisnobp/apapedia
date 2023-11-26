package com.apapedia.order.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apapedia.order.DTO.CartMapper;
import com.apapedia.order.DTO.request.CartItemDTO;
import com.apapedia.order.model.Cart;
import com.apapedia.order.model.CartItem;
import com.apapedia.order.service.CartServiceimpl;
import com.apapedia.order.service.OrderServiceimpl;

@Controller
@RestController
@RequestMapping("/orderservice")
public class CartController {
    

    @Autowired
    CartServiceimpl cartServiceimpl;

    @Autowired
    OrderServiceimpl orderServiceimpl;

    @Autowired
    CartMapper cartMapper;

    // membuat Cart ketika User dibuat
    @PostMapping("cart/create/{userId}")
    public Cart createCart(@PathVariable("userId") UUID userId){
        
        Cart cartBaru = new Cart();

        cartBaru.setUserId(userId);
        cartBaru.setTotalPrice(0);

        cartBaru.setListCartItem(new ArrayList<>());

        return cartServiceimpl.createCart(cartBaru);
    }

    // Mendapatkan CartItem berdasarkan  User Id
    @GetMapping("cart/cartitem/{userId}")
    public List<CartItem> getCartItem(@PathVariable("userId") UUID userId){

        var listCart = cartServiceimpl.getCartByUserId(userId);

        return listCart.getListCartItem();



    }

    // Menambahkan CartItem baru pada Cart tertentu
    @PostMapping("cart/cartItem/create")
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
