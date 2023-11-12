package com.apapedia.user.service.user;

import com.apapedia.user.dto.response.UserDataResponse;
import com.apapedia.user.model.User;

import java.util.UUID;

public interface UserService {
    UserDataResponse findUserById(UUID id);
    String getSellerCategory(UUID id);
    UUID getCustomerCartId(UUID id);
    void deleteUser(UUID id);
}
