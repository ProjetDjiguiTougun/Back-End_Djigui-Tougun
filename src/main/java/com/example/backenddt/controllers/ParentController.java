package com.example.backenddt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parent")
public class ParentController {
    @GetMapping
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Bienvenue a vous Cher parent");
    }
}
