package com.example.backenddt.controllers;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/parrain")
public class ParrainController {
    @GetMapping
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Bienvenue a toi le parrain");
    }

    //Pour extraire les informations depuis keycloak:
    @GetMapping("/me")
    public Map<String, Object> getMe(@AuthenticationPrincipal Jwt jwt) {
        return Map.of(
                "username", jwt.getClaim("preferred_username"),
                "email",jwt.getClaim("email"),
                "roles", jwt.getClaim("realm_access")
        );
    }

}
