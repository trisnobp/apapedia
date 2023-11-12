package com.apapedia.order.controller;

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

import com.apapedia.order.DTO.CartItemDTO;
import com.apapedia.order.DTO.CartMapper;
import com.apapedia.order.model.Cart;
import com.apapedia.order.model.CartItem;
import com.apapedia.order.service.CartServiceimpl;
import com.apapedia.order.service.OrderServiceimpl;

@Controller
@RestController
@RequestMapping("/Order")
public class CartController {
    

    @Autowired
    CartServiceimpl cartServiceimpl;

    @Autowired
    OrderServiceimpl orderServiceimpl;

    @Autowired
    CartMapper cartMapper;



    @PostMapping("/cart/create/{userId}")
    public void createCart(@PathVariable("userId") UUID userId){
        
        Cart cartBaru = new Cart();

        cartBaru.setUserId(userId);
        cartBaru.setTotalPrice(0);

        cartServiceimpl.createCart(cartBaru);
    }

    @GetMapping("cartitem/{userId}")
    public List<CartItem> getCartItem(@PathVariable("userId") UUID userId){

        Cart cart = cartServiceimpl.getCartByUserId(userId);

        return cart.getListCartItem();



    }

    @PostMapping("cartItem/create")
    public CartItem addCartItem(@RequestBody CartItemDTO cartItemDTO ) {

        CartItem cartItem = cartMapper.CartItemDTOToCartItem(cartItemDTO);
       
        cartServiceimpl.createCartItemBaru(cartItem);
        
        return cartItem;

    }

}
