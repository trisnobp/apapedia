package com.apapedia.frontend.service.user;

import com.apapedia.frontend.DTO.request.LoginRequestDTO;
import com.apapedia.frontend.DTO.request.RegisterRequestDTO;
import com.apapedia.frontend.DTO.request.TokenDTO;
import com.apapedia.frontend.DTO.response.LoginResponseDTO;
import com.apapedia.frontend.DTO.response.RegisterResponseDTO;
import com.apapedia.frontend.DTO.response.UserDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
public class UserService {

    private final WebClient webClient;

    public UserService(WebClient.Builder webClientBuilder) {
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
                .bodyToMono(Boolean.class);

        System.out.println(response);
        return response.block().booleanValue();
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        var response = this.webClient
                .post()
                .uri("/account/sellerLogin")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(loginRequestDTO)
                .retrieve()
                .bodyToMono(LoginResponseDTO.class);

        return response.block();
    }

    public RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        var response = this.webClient
                .post()
                .uri("/account/register")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(registerRequestDTO)
                .retrieve()
                .bodyToMono(RegisterResponseDTO.class);

        return response.block();
    }

    public UserDTO getUserData(String token) {
        // Get the Id from token first
        var userId = this.webClient
                .get()
                .uri("/user")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Get the user data by the id
        var userData = this.webClient
                .get()
                .uri("/user/" + UUID.fromString(userId))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToMono(UserDTO.class)
                .block();

        return userData;
    }

}
