package com.apapedia.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.apapedia.order.repository.CartDb;
import com.apapedia.order.repository.CartItemDb;

import jakarta.transaction.Transactional;

@Service
@Transactional
@ComponentScan(basePackages = "com.apapedia")
public class OrderServiceimpl implements OrderService{

    @Autowired
    CartDb cartDb;

    @Autowired
    CartItemDb cartItemDb;
    
}
