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

	@Bean
	@Transactional
	CommandLineRunner run(CartService cartService ,OrderService orderService) {
		return args -> {

			Cart cart = new Cart();

			CartItem cartItem = new CartItem();
			
			cart.setUserId(UUID.randomUUID());

			cartItem.setCart(cart);
			cartItem.setProductId(UUID.randomUUID());
			cartItem.setQuantity(2);

			List arryList = new ArrayList<>();

			cart.setListCartItem(arryList);

			cart.getListCartItem().add(cartItem);

			cart.setTotalPrice(0);


			cartService.createCart(cart);
			

			cartService.createCartItemBaru(cartItem);

			Order order = new Order();

			OrderItem orderItem = new OrderItem();

			// order.setIdOrder(UUID.randomUUID());

			order.setCustomer(UUID.randomUUID());

			order.setSeller(UUID.randomUUID());

			order.setTotalPrice(1000);

			

			List<OrderItem> listOrderitem = new ArrayList<>();

			// OrderItemId orderItemId = new OrderItemId();

			// orderItemId.setOrderId(UUID.randomUUID());

			// orderItemId.setProductId(UUID.randomUUID());

			// orderItem.setOrderItemId(orderItemId);

			orderItem.setProductPrice(1000);

			orderItem.setProductId(UUID.randomUUID());

			orderItem.setProductName("Good");

			orderItem.setQuantity(2);

			listOrderitem.add(orderItem);

			order.setListOrderItem(listOrderitem);

			var orderTersimpan = orderService.createOrder(order);

			// orderService.createOrderItem(orderItem,orderTersimpan.getIdOrder() );






		

		
	};
}

}
