package com.apapedia.user.controller;

import com.apapedia.user.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") UUID id) {
        var user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }
}
