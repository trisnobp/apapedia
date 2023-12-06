package com.apapedia.user.service.user;

import com.apapedia.user.dto.UserMapper;
import com.apapedia.user.dto.request.UpdateUserDataRequest;
import com.apapedia.user.dto.response.UpdateUserBalanceResponse;
import com.apapedia.user.dto.response.UpdateUserDataResponse;
import com.apapedia.user.dto.response.UserDataResponse;
import com.apapedia.user.model.Customer;
import com.apapedia.user.model.Role;
import com.apapedia.user.model.User;
import com.apapedia.user.repository.CustomerDb;
import com.apapedia.user.repository.SellerDb;
import com.apapedia.user.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDataResponse findUserById(UUID id) {
        var user =  userDb.findById(id).get();
        var userDTO = userMapper.userToUserDataResponse(user);
        // Check if the role is Seller
        if (user.getRole() == Role.SELLER) {
            userDTO.setRole("Seller");
            userDTO.setCategory(getSellerCategory(id));
        } else {
            userDTO.setRole("Customer");
            userDTO.setCartId(getCustomerCartId(id));
        }
        return userDTO;
    }

    @Override
    public UpdateUserDataResponse updateUser(UpdateUserDataRequest request) {
        var user = userDb.findById(request.getId()).get();

        if (request.getPassword() != null) {
            // Cek kesamaan password
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return UpdateUserDataResponse.builder().status(false)
                        .message("Password baru tidak boleh sama dengan password lama.").build();
            }
        }

        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        if (request.getRole().equals("CUSTOMER")) {
            if (request.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(request.getPassword()));
            }
        }
        user.setAddress(request.getAddress());
        user.setUpdatedAt(LocalDateTime.now());
        userDb.save(user);

        if (user.getRole().getRoleName().equals("SELLER")) {
            return UpdateUserDataResponse.builder().status(true)
                    .message("Akun berhasil di-update.").build();
        } else {
            return UpdateUserDataResponse.builder().status(true)
                    .message("Akun berhasil di-update. Silakan coba login kembali.").build();
        }
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

    @Override
    public UpdateUserBalanceResponse addMoney(UUID id, Long amount) {
        var user = userDb.findById(id).get();
        user.setBalance(user.getBalance() + amount);
        userDb.save(user);
        return UpdateUserBalanceResponse.builder().status(true)
                .message("Saldo berhasil ditambahkan!")
                .balance(user.getBalance()).build();
    }

    @Override
    public UpdateUserBalanceResponse withdrawMoney(UUID id, Long amount) {
        var user = userDb.findById(id).get();
        // Validasi apakah amount melebih jumlah balance yang dimiliki
        if (amount > user.getBalance()) {
            return UpdateUserBalanceResponse.builder().status(false)
                    .message("Jumlah uang yang diminta melebihi jumlah saldo yang dimiliki.")
                    .balance(user.getBalance()).build();
        }

        user.setBalance(user.getBalance() - amount);
        userDb.save(user);
        return UpdateUserBalanceResponse.builder().status(true)
                .message("Saldo berhasil diambil!")
                .balance(user.getBalance()).build();
    }
}
