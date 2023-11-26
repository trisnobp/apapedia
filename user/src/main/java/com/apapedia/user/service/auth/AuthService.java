package com.apapedia.user.service.auth;

import com.apapedia.user.dto.request.LoginRequest;
import com.apapedia.user.dto.response.LoginResponse;
import com.apapedia.user.dto.request.RegisterRequest;
import com.apapedia.user.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);
    LoginResponse loginForSeller(LoginRequest request);
    LoginResponse loginForCustomer(LoginRequest request);
    LoginResponse loginWithSSO(LoginRequest request);
}
