package com.example.backenddt.controllers;

import com.example.backenddt.requeteDTO.UserRequestDTO;
import com.example.backenddt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO users){
        return userService.createUser(users);
    }
}
