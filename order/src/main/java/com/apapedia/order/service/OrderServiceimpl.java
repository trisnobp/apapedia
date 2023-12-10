package com.apapedia.order.service;

import java.time.LocalDateTime;
import java.util.*;

import com.apapedia.order.dto.CatalogueMapper;
import com.apapedia.order.dto.request.CreateOrderRequestDTO;
import com.apapedia.order.dto.request.OrderItemDTO;
import com.apapedia.order.dto.request.UpdateOrderRequestDTO;
import com.apapedia.order.dto.response.CreateOrderResponseDTO;
import com.apapedia.order.dto.response.ResponseCatalogueDTO;
import com.apapedia.order.dto.response.UpdateOrderResponseDTO;
import com.apapedia.order.dto.response.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
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
    @Autowired
    CatalogueMapper catalogueMapper;

    private final WebClient webClientCatalogue;
    private final WebClient webClientUser;

    public OrderServiceimpl(WebClient.Builder webClientBuilder) {
        this.webClientCatalogue = webClientBuilder.baseUrl("http://localhost:8081/api").build(); // Server Order
        this.webClientUser = webClientBuilder.baseUrl("http://localhost:8080/api").build();
    }

    @Override
    public CreateOrderResponseDTO createOrder(String token, CreateOrderRequestDTO createOrderRequestDTO) {
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

            for (var item: listOrderItem) {
                newOrder.setTotalPrice(newOrder.getTotalPrice() + (item.getProductPrice() * item.getQuantity()));
            }

            newOrder.setCustomer(createOrderRequestDTO.getCustomer());
            newOrder.setSeller(createOrderRequestDTO.getSeller());

            var orderTersimpan = orderDb.save(newOrder);

            for (OrderItemDTO orderItem : listOrderItem){
                createOrderItem(orderItem, orderTersimpan.getIdOrder());
            }

            // Subtract the customer's balance after the order process is done
            var subtractUserBalance = this.webClientUser
                    .put()
                    .uri("/user/{id}/balance?withdraw={withdraw}", orderTersimpan.getCustomer(), orderTersimpan.getTotalPrice())
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToMono(String.class).block();

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
        // Consume API untuk ambil stok produk
        var catalogueDataResponse = this.webClientCatalogue
                .get()
                .uri("/catalogue/" + productId)
                .retrieve()
                .bodyToMono(ResponseCatalogueDTO.class).block();

        // Check the stock
       return orderAmount <= catalogueDataResponse.getStock();
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

        // Get the catalogue data
        var catalogueDataResponse = this.webClientCatalogue
                .get()
                .uri("/catalogue/" + orderItemId.getProductId())
                .retrieve()
                .bodyToMono(ResponseCatalogueDTO.class).block();

        // Update the catalogue stock by subtracting it
        catalogueDataResponse.setStock(catalogueDataResponse.getStock() - orderItem.getQuantity());
        var updateCatalogueRequest = catalogueMapper.catalogueToUpdateCatalogueRequestDTO(catalogueDataResponse);

        var updateCatalogueResponse = this.webClientCatalogue
                .put()
                .uri("/catalogue/" + updateCatalogueRequest.getId() + "/update")
                .bodyValue(updateCatalogueRequest)
                .retrieve()
                .bodyToMono(String.class).block();
    }

    @Override
    public List<Order> getOrderByIdCustomer(UUID customerId) {
        return orderDb.findByCustomer(customerId);
    }

    @Override
    public List<Order> getOrderByIdSeller(UUID sellerId) {
        return orderDb.findBySeller(sellerId);
    }

    @Override
    public UpdateOrderResponseDTO updateOrderStatus(String token, UpdateOrderRequestDTO requestDTO) {
        try {
            var order = orderDb.findByIdOrder(requestDTO.getOrderId());
            order.setStatus(requestDTO.getStatus());
            orderDb.save(order);

            if (requestDTO.getStatus() == 5) { // Kalau pembeli tekan selesai
                // Update seller balance (ditambahin)
                var updateSellerBalance = this.webClientUser
                        .put()
                        .uri("/user/{id}/balance?add={add}", order.getSeller(), order.getTotalPrice())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .retrieve()
                        .bodyToMono(String.class).block();
            }

            return UpdateOrderResponseDTO.builder()
                    .status(true)
                    .message("Status pesanan berhasil diubah.")
                    .build();

        } catch (Exception e) {
            return UpdateOrderResponseDTO.builder()
                    .status(false)
                    .message("Terdapat kesalahan dalam proses update.")
                    .build();
        }
    }
    @Override
    public HashMap<String, Long> getTopFiveProductsThisMonth(UUID sellerId) {
        HashMap<String, Long> mapForProducts = new HashMap<>();
        var topFiveProducts = orderDb.findProductNamesAndCounts(sellerId);
        if (!topFiveProducts.isEmpty()) {
            int counter = 0;
            for (var item: topFiveProducts) {
                if (counter == 5) {
                    break;
                }
                mapForProducts.put(item.getProductName(), item.getOrderItemCount());
                counter++;
            }
        }

        return mapForProducts;
    }

    @Override
    public void softDeleteOrderByOrderId(UUID orderId) {
        var orderById = orderDb.findById(orderId).get();
        orderDb.delete(orderById);
    }
}
