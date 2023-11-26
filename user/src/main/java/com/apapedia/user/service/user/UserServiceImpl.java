package com.apapedia.user.service.user;

import com.apapedia.user.dto.UserMapper;
import com.apapedia.user.dto.response.UserDataResponse;
import com.apapedia.user.model.Customer;
import com.apapedia.user.model.Role;
import com.apapedia.user.model.User;
import com.apapedia.user.repository.CustomerDb;
import com.apapedia.user.repository.SellerDb;
import com.apapedia.user.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDb userDb;
    @Autowired
    CustomerDb customerDb;
    @Autowired
    SellerDb sellerDb;
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDataResponse findUserById(UUID id) {
        var user =  userDb.findById(id).get();
        var userDTO = userMapper.userToUserDataResponse(user);
        // Check if the role is Seller
        if (user.getRole() == Role.SELLER) {
            userDTO.setCategory(getSellerCategory(id));
        } else {
            userDTO.setCartId(getCustomerCartId(id));
        }
        return userDTO;
    }

    @Override
    public String getSellerCategory(UUID id) {
        return sellerDb.findById(id).get().getCategory();
    }

    @Override
    public UUID getCustomerCartId(UUID id) {
        return customerDb.findById(id).get().getCartId();
    }

    @Override
    public void deleteUser(UUID id) {
        userDb.deleteById(id);
    }
}
