package com.apapedia.user.controller;

import com.apapedia.user.dto.request.LoginRequest;
import com.apapedia.user.dto.request.RegisterRequest;
import com.apapedia.user.dto.response.LoginResponse;
import com.apapedia.user.dto.response.RegisterResponse;
import com.apapedia.user.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @RequestBody RegisterRequest request
    ) {
        var registration = authService.register(request);
        return ResponseEntity.ok(registration);
    }

    @PostMapping("/customerLogin")
    public ResponseEntity<LoginResponse> loginForCustomer(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/sellerLogin")
    public ResponseEntity<LoginResponse> loginForSeller(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }
}
