package com.apapedia.user.service.user;

import com.apapedia.user.dto.request.UpdateUserDataRequest;
import com.apapedia.user.dto.response.UpdateUserBalanceResponse;
import com.apapedia.user.dto.response.UpdateUserDataResponse;
import com.apapedia.user.dto.response.UserDataResponse;
import com.apapedia.user.model.User;

import java.util.UUID;

public interface UserService {
    UserDataResponse findUserById(UUID id);
    String getSellerCategory(UUID id);
    UUID getCustomerCartId(UUID id);
    void deleteUser(UUID id);
    UpdateUserDataResponse updateUser(UpdateUserDataRequest request);
    UpdateUserBalanceResponse addMoney(UUID id, Long amount);
    UpdateUserBalanceResponse withdrawMoney(UUID id, Long amount);
}
