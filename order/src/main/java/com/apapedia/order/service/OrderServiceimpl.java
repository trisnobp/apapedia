package com.apapedia.order.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.apapedia.order.dto.request.CreateOrderRequestDTO;
import com.apapedia.order.dto.request.OrderItemDTO;
import com.apapedia.order.dto.response.CreateOrderResponseDTO;
import com.apapedia.order.dto.response.ResponseCatalogueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.apapedia.order.model.Order;
import com.apapedia.order.model.OrderItem;
import com.apapedia.order.model.OrderItemId;
import com.apapedia.order.repository.CartDb;
import com.apapedia.order.repository.CartItemDb;
import com.apapedia.order.repository.OrderDb;
import com.apapedia.order.repository.OrderItemDb;

import jakarta.transaction.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Transactional
@ComponentScan(basePackages = "com.apapedia")
public class OrderServiceimpl implements OrderService {
    @Autowired
    CartDb cartDb;
    @Autowired
    CartItemDb cartItemDb;
    @Autowired
    OrderDb orderDb;
    @Autowired
    OrderItemDb orderItemDb;
    private final WebClient webClientCatalogue;

    public OrderServiceimpl(WebClient.Builder webClientBuilder) {
        this.webClientCatalogue = webClientBuilder.baseUrl("http://localhost:8081/api").build(); // Server Order
    }

    @Override
    public CreateOrderResponseDTO createOrder(CreateOrderRequestDTO createOrderRequestDTO) {
        List<OrderItemDTO> listOrderItem = new ArrayList<>();

        if (createOrderRequestDTO.getListOrderItem() != null) {
            listOrderItem = createOrderRequestDTO.getListOrderItem();
            createOrderRequestDTO.setListOrderItem(new ArrayList<>());

            // Untuk setiap barang yang dipesan, kita cek ke catalog service dulu berapa stok-nya
            for (var item: listOrderItem) {
                if (!isStockAvailable(item.getProductId(), item.getQuantity())) {
                    return CreateOrderResponseDTO.builder()
                            .status(false)
                            .message("Jumlah produk " + item.getProductName() + " yang dipesan melebihi jumlah stok yang tersedia.").
                            build();
                }
            }

            Order newOrder = new Order();
            newOrder.setCreatedAt(LocalDateTime.now());
            newOrder.setUpdatedAt(LocalDateTime.now());
            newOrder.setStatus(0);

            // Calculate totalPrice
            for (var item: listOrderItem) {
                newOrder.setTotalPrice(newOrder.getTotalPrice() + item.getProductPrice());
            }

            newOrder.setCustomer(createOrderRequestDTO.getCustomer());
            newOrder.setSeller(createOrderRequestDTO.getSeller());

            var orderTersimpan = orderDb.save(newOrder);

            for (OrderItemDTO orderItem : listOrderItem){
                createOrderItem(orderItem, orderTersimpan.getIdOrder());
            }

            return CreateOrderResponseDTO.builder()
                    .status(true)
                    .message("Order dengan id " + orderTersimpan.getIdOrder() + " berhasil dibuat")
                    .orderId(orderTersimpan.getIdOrder())
                    .build();
        } else {
            return CreateOrderResponseDTO.builder()
                            .status(false)
                                    .message("Tidak ada barang yang dipesan").
                    build();
        }
    }

    // Cek ketersediaan produk/katalog di database
    public boolean isStockAvailable(UUID productId, int orderAmount) {
        // Consume API untuk buat cart di Order Service, terus pass UUID cart ke sini
        var catalogueDataResponse = this.webClientCatalogue
                .get()
                .uri("/catalogue/" + productId)
                .retrieve()
                .bodyToMono(ResponseCatalogueDTO.class).block();

        // Check the stock
       return orderAmount >= catalogueDataResponse.getStock();
    }

    @Override
    public void createOrderItem(OrderItemDTO orderItem, UUID orderId) {
        OrderItemId orderItemId = new OrderItemId(orderId, orderItem.getProductId());
        var order = orderDb.findByIdOrder(orderId);

        // Create order item
        OrderItem newOrderItem = new OrderItem();
        newOrderItem.setOrder(order);
        newOrderItem.setQuantity(orderItem.getQuantity());
        newOrderItem.setOrderItemId(orderItemId);
        newOrderItem.setProductName(orderItem.getProductName());
        newOrderItem.setProductPrice(orderItem.getProductPrice());

        orderItemDb.save(newOrderItem);
    }

    @Override
    public List<Order> getOrderByIdCustomer(UUID customerId) {
        return orderDb.findByCustomer(customerId);
    }
    
}
