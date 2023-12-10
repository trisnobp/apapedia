package com.apapedia.frontend.service.order;

import com.apapedia.frontend.DTO.request.UpdateOrderStatusRequestDTO;
import com.apapedia.frontend.DTO.response.OrderDTO;
import com.apapedia.frontend.DTO.response.UpdateOrderStatusResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final WebClient webClient;

    public OrderService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082/api").build(); // Server User
    }

    public HashMap<String, Long> getTopFiveProducts(UUID sellerId) {
        // Get Top Five Products
        var topFiveProducts = this.webClient
                .get()
                .uri("/order/{idOrder}/top-products", sellerId)
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();

        return topFiveProducts;
    }

    public List<OrderDTO> getOrderBySellerId(UUID sellerId) {
        var sellerOrder = this.webClient
                .get()
                .uri("/order/seller/{idSeller}", sellerId)
                .retrieve()
                .bodyToFlux(OrderDTO.class)
                .collectList().block();

        return sellerOrder;
    }

    public UpdateOrderStatusResponseDTO updateOrderStatus(String token, UpdateOrderStatusRequestDTO requestDTO) {
        var updateDTO = this.webClient
                .put()
                .uri("/order/{idOrder}/update", requestDTO.getOrderId())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .bodyValue(requestDTO)
                .retrieve()
                .bodyToMono(UpdateOrderStatusResponseDTO.class)
                .block();

        return updateDTO;
    }

    public void deleteSellerOrder(UUID orderId) {
        var deleteOrder = this.webClient
                .delete()
                .uri("/order/{idOrder}/delete", orderId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
