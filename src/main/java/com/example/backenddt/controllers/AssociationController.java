package com.example.backenddt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/association")
public class AssociationController {
    @GetMapping
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Bienvenue a vous Admin de l'Association");
    }
}
