package com.apapedia.frontend.service.user;

import com.apapedia.frontend.DTO.request.LoginRequestDTO;
import com.apapedia.frontend.DTO.request.RegisterRequestDTO;
import com.apapedia.frontend.DTO.request.TokenDTO;
import com.apapedia.frontend.DTO.response.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class UserService {

    private final WebClient webClient;

    public UserService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("apap-052.cs.ui.ac.id/api").build(); // Server User
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

    public UserDTO getUserById(String token, UUID id) {
        // Get the user data by the id
        var userData = this.webClient
                .get()
                .uri("/user/" + id)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToMono(UserDTO.class)
                .block();

        return userData;
    }
    public UpdateUserDataResponse updateUserData(UserDTO userDTO, String token) {

        var updatedUserData = this.webClient
                .put()
                .uri("/user/" + userDTO.getId() + "/update")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .bodyValue(userDTO)
                .retrieve()
                .bodyToMono(UpdateUserDataResponse.class)
                .block();

        return updatedUserData;
    }

    public UpdateUserBalanceResponse updateUserBalance(Long saldo, String token, UUID id) {

        var updatedUserBalance = this.webClient
                .put()
                .uri("/user/{id}/balance?withdraw={withdraw}", id, saldo)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToMono(UpdateUserBalanceResponse.class)
                .block();

        return updatedUserBalance;
    }

    public String deleteSellerAccount(UUID id, String token) {
        var deleteSeller = this.webClient
                .delete()
                .uri("/user/{id}/delete", id)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return deleteSeller;
    }
}
