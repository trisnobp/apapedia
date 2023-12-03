package com.apapedia.user.controller;

import com.apapedia.user.config.JwtService;
import com.apapedia.user.dto.request.TokenRequest;
import com.apapedia.user.dto.request.UpdateUserDataRequest;
import com.apapedia.user.dto.response.UpdateUserBalanceResponse;
import com.apapedia.user.dto.response.UpdateUserDataResponse;
import com.apapedia.user.dto.response.UserDataResponse;
import com.apapedia.user.service.user.UserService;
import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;

    @GetMapping("")
    public ResponseEntity<String> getLoggedInUserId(
            @RequestHeader("Authorization") String token
    ) {
        try {
            Claims claims = jwtService.extractAllClaims(token.substring(7));
            return ResponseEntity.ok((String) claims.get("id"));
        } catch (Exception e) {
            return ResponseEntity.ok("Invalid Token");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDataResponse> getUserById(
            @RequestHeader("Authorization") String token,
            @PathVariable("id") UUID id
    ) {
        var user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteUser(
            @PathVariable("id") UUID id
    ) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User berhasil di-delete.");
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<UpdateUserDataResponse> updateUser(
            @Valid @RequestBody UpdateUserDataRequest updateUserDataRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        } else {
            var updatedUser = userService.updateUser(updateUserDataRequest);
            return ResponseEntity.ok(updatedUser);
        }
    }

    @PutMapping("/{id}/balance")
    public ResponseEntity<UpdateUserBalanceResponse> updateBalance(
            @PathVariable("id") UUID id,
            @RequestParam(value = "add", required = false) Long addMoneyAmount,
            @RequestParam(value = "withdraw", required = false) Long withdrawMoneyAmount
    ) {
        // Bagi jadi dua kasus, antara dia mau withdraw saldo atau tambah saldo
        if (addMoneyAmount != null) {
            var response = userService.addMoney(id, addMoneyAmount);
            return ResponseEntity.ok(response);
        } else {
            var response = userService.withdrawMoney(id, withdrawMoneyAmount);
            return ResponseEntity.ok(response);
        }
    }
}
