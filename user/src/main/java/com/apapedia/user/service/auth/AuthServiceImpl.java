package com.apapedia.user.service.auth;

import com.apapedia.user.config.JwtService;
import com.apapedia.user.dto.request.LoginRequest;
import com.apapedia.user.dto.response.CreateUserCartResponse;
import com.apapedia.user.dto.response.LoginResponse;
import com.apapedia.user.dto.request.RegisterRequest;
import com.apapedia.user.dto.response.RegisterResponse;
import com.apapedia.user.model.Customer;
import com.apapedia.user.model.Role;
import com.apapedia.user.model.Seller;
import com.apapedia.user.model.User;
import com.apapedia.user.repository.CustomerDb;
import com.apapedia.user.repository.SellerDb;
import com.apapedia.user.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserDb userDb;
    @Autowired
    CustomerDb customerDb;
    @Autowired
    SellerDb sellerDb;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final WebClient webClient;

    public AuthServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("apap-050.cs.ui.ac.id/api").build(); // Server Order
    }


    @Override
    public RegisterResponse register(RegisterRequest request) {
        // Validasi untuk check apakah email/username/password unik
        var findUserByUsername = userDb.findByUsername(request.getUsername()).orElse(null);
        var findUserByEmail = userDb.findByEmail(request.getEmail()).orElse(null);
        if (request.getPassword() == null) {
            request.setPassword("SSOPassword");
        }
        var findUserByPassword = userDb.findByPassword(passwordEncoder.encode(request.getPassword())).orElse(null);
        var response = RegisterResponse.builder();

        if (findUserByEmail != null || findUserByUsername != null || findUserByPassword != null) {
            response.status(false); // Indicates failure
            response.message("Username or Email has been used.");

            return response.build();

        } else {
            response.status(true); // Indicates success
            response.message("User is successfully registered.");
            // Assign Roles
            var role = (request.getCategory() != null) ? Role.SELLER : Role.CUSTOMER;
            // Save user
            var user = User.builder().address(request.getAddress())
                    .email(request.getEmail()).name(request.getName())
                    .role(role).username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now()).build();

            // Simpan user ke database
            var savedUser = userDb.save(user);

            // Input data ke table customer/seller
            if (role == Role.CUSTOMER) {
                // Consume API untuk buat cart di Order Service, terus pass UUID cart ke sini
                var cartDataResponse = this.webClient
                        .post()
                        .uri("/cart/create/" + savedUser.getId())
                        .retrieve()
                        .bodyToMono(CreateUserCartResponse.class).block();


                var customer = Customer.builder().userId(savedUser.getId()).cartId(cartDataResponse.getIdCart()).build();
                customerDb.save(customer);
            } else {
                var seller = Seller.builder().userId(savedUser.getId()).category(request.getCategory()).build();
                sellerDb.save(seller);
            }

            return response.build();
        }
    }

    @Override
    public LoginResponse loginForSeller(LoginRequest request) {
        try {
            var user = (isInputEmail(request.getUsernameOrEmail())) ? userDb.findByEmail(request.getUsernameOrEmail()).get()
                    : userDb.findByUsername(request.getUsernameOrEmail()).get();

            if (user.getRole().getRoleName().equals("CUSTOMER")) {
                return LoginResponse.builder().status(false)
                        .message("Account isn't registered as Seller.").build();
            }

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsernameOrEmail(),
                            request.getPassword()
                    )
            );

            var jwtToken = jwtService.createToken(user);
            return LoginResponse.builder()
                    .status(true)
                    .message("You are successfully logged in!")
                    .token(jwtToken)
                    .build();
        } catch (Exception e) {
            return LoginResponse.builder()
                    .status(false)
                    .message("No such element")
                    .build();
        }
    }

    @Override
    public LoginResponse loginForCustomer(LoginRequest request) {
        try {
            var user = (isInputEmail(request.getUsernameOrEmail())) ? userDb.findByEmail(request.getUsernameOrEmail()).get()
                    : userDb.findByUsername(request.getUsernameOrEmail()).get();

            if (user.getRole().getRoleName().equals("SELLER")) {
                return LoginResponse.builder().status(false)
                        .message("Account isn't registered as Customer.").build();
            }

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsernameOrEmail(),
                            request.getPassword()
                    )
            );

            var jwtToken = jwtService.createToken(user);
            return LoginResponse.builder()
                    .status(true)
                    .message("You are successfully logged in!")
                    .token(jwtToken)
                    .build();
        } catch (Exception e) {
            return LoginResponse.builder()
                    .status(false)
                    .message("Please check your credentials again.")
                    .build();
        }
    }

    @Override
    public LoginResponse loginWithSSO(LoginRequest request) {
        return null;
    }

    // Buat cek apakah input berupa email atau bukan
    public boolean isInputEmail(String request) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (request == null)
            return false;
        return pat.matcher(request).matches();
    }
}
