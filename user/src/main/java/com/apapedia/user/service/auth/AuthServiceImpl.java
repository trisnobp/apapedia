package com.apapedia.user.service.auth;

import com.apapedia.user.dto.request.CreateUserCartRequest;
import com.apapedia.user.dto.request.LoginRequest;
import com.apapedia.user.dto.response.LoginResponse;
import com.apapedia.user.dto.request.RegisterRequest;
import com.apapedia.user.dto.response.RegisterResponse;
import com.apapedia.user.dto.response.CreateUserCartResponse;
import com.apapedia.user.model.Customer;
import com.apapedia.user.model.Role;
import com.apapedia.user.model.Seller;
import com.apapedia.user.model.User;
import com.apapedia.user.repository.CustomerDb;
import com.apapedia.user.repository.SellerDb;
import com.apapedia.user.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserDb userDb;
    @Autowired
    CustomerDb customerDb;
    @Autowired
    SellerDb sellerDb;
    @Autowired
    PasswordEncoder passwordEncoder;

    private final WebClient webClient;

    public AuthServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082/api").build(); // Server Order
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        // Validasi untuk check apakah email/username/password unik
        var findUserByUsername = userDb.findByUsername(request.getUsername()).orElse(null);
        var findUserByEmail = userDb.findByEmail(request.getEmail()).orElse(null);
        var findUserByPassword = userDb.findByPassword(passwordEncoder.encode(request.getPassword())).orElse(null);
        var response = RegisterResponse.builder();

        if (findUserByEmail != null || findUserByUsername != null || findUserByPassword != null) {
            response.status(400); // Indicates failure
            response.message("Username atau Email sudah pernah digunakan.");

            return response.build();

        } else {
            response.status(200); // Indicates success
            response.message("User berhasil didaftarkan.");
            // Assign Roles
            var role = (request.getCategory() != null) ? Role.SELLER : Role.CUSTOMER;
            // Save user
            var user = User.builder().address(request.getAddress())
                    .email(request.getEmail()).name(request.getName())
                    .role(role).username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .createdAt(LocalDateTime.now()).build();

            // Simpan user ke database
            var savedUser = userDb.save(user);

            // Input data ke table customer/seller
            if (role == Role.CUSTOMER) {
                // Consume API untuk create cart di Order Service, terus pass UUID cart ke sini
                var cartRequest = new CreateUserCartRequest(savedUser.getId());

                var cartDataResponse = this.webClient
                        .post()
                        .uri("/cart/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(cartRequest)
                        .retrieve()
                        .bodyToMono(CreateUserCartResponse.class).block();

                var customer = Customer.builder().userId(savedUser.getId()).cartId(cartDataResponse.getId()).build();
                customerDb.save(customer);

            } else {
                var seller = Seller.builder().userId(savedUser.getId()).category(request.getCategory()).build();
                sellerDb.save(seller);
            }

            return response.build();
        }
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        return null;
    }
}
