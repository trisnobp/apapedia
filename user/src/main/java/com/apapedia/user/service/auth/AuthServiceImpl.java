package com.apapedia.user.service.auth;

import com.apapedia.user.dto.request.LoginRequest;
import com.apapedia.user.dto.response.LoginResponse;
import com.apapedia.user.dto.request.RegisterRequest;
import com.apapedia.user.dto.response.RegisterResponse;
import com.apapedia.user.model.Role;
import com.apapedia.user.model.Seller;
import com.apapedia.user.model.User;
import com.apapedia.user.repository.CustomerDb;
import com.apapedia.user.repository.SellerDb;
import com.apapedia.user.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

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
                // Consume API untuk buat cart di Order Service, terus pass UUID cart ke sini
                // var user = Customer.builder().userId(saveUser.getId()).cartId());
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
