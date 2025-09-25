package com.example.backenddt.controllers;

import com.example.backenddt.entites.Parrain;
import com.example.backenddt.entites.User;
import com.example.backenddt.repository.ParrainRepository;
import com.example.backenddt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ParrainRepository parrainRepository;

    @Autowired
    private UserRepository userRepository;

    public Parrain getParrain(Jwt jwt){
//        @AuthenticationPrincipal
        User user = userRepository.findByEmail(jwt.getClaim("email"));
        try {
            return parrainRepository.findByUser(user);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Bienvenue a toi le parrain");
    }

    //Pour la recuperation de parrain connecter :


    //Pour extraire les informations depuis keycloak:
    @GetMapping("/me")
    public Map<String, Object> getMe(@AuthenticationPrincipal Jwt jwt) {

        if (getParrain(jwt)!=null){
            Parrain parrain = getParrain(jwt);
            return Map.of(
                    "nom",parrain.getNom(),
                    "prenom",parrain.getPrenom(),
                    "roles",jwt.getClaim("roles")
            );
        }
        return Map.of(
                "erreur","Veillez vous connecter !!!"
        );

    }

}
