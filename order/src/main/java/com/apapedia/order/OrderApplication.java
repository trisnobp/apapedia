package com.apapedia.order;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.apapedia.order.model.Cart;
import com.apapedia.order.model.CartItem;
import com.apapedia.order.model.Order;
import com.apapedia.order.model.OrderItem;
import com.apapedia.order.model.OrderItemId;
import com.apapedia.order.service.CartService;
import com.apapedia.order.service.OrderService;

import jakarta.transaction.Transactional;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = "com.apapedia.order")
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}
}

