package com.apapedia.user.controller;

import com.apapedia.user.dto.response.UserDataResponse;
import com.apapedia.user.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<UserDataResponse> getUser(@PathVariable("id") UUID id) {
        var user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User berhasil di-delete.");
    }
}
