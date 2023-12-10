package com.apapedia.catalogue.service;
import com.apapedia.catalogue.DTO.request.TokenDTO;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class UserServiceImpl {
    private final WebClient webClient;

    public UserServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/api").build(); // Server User
    }

    public boolean checkTokenValidity(String token) {
        var tokenDTO = new TokenDTO();
        tokenDTO.setToken(token);

        var response = this.webClient
                .post()
                .uri("/account/check-token-validity")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(tokenDTO)
                .retrieve()
                .bodyToMono(boolean.class);

        return response.block();
    }
}

