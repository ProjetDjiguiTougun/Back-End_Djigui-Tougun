package com.example.backenddt.services;

import com.example.backenddt.entites.Parrain;
import com.example.backenddt.entites.User;
import com.example.backenddt.repository.ParrainRepository;
import com.example.backenddt.repository.UserRepository;
import com.example.backenddt.requeteDTO.ParrainRequestDTO;
import com.example.backenddt.requeteDTO.UserRequestDTO;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ParrainService {



    @Autowired
    private ParrainRepository parrainRepository;
    @Autowired
    private UserRepository userRepository;

    private final KeycloakService keycloakService;

    public ParrainService(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }

    public ResponseEntity<?> createParrain(ParrainRequestDTO users) {
        try {
            boolean result = keycloakService.createUserWithRole(
                    users.nom,
                    users.prenom,
                    users.email,
                    users.password,
                    users.role
            );

            if (result) {
                User user = new User();
                user.setEmail(users.email);
                user.setPassword(users.password);
                user.setRole(users.role);
                userRepository.save(user);

                Parrain parrain = new Parrain();
                parrain.setAdresse(users.adresse);
                parrain.setNom(users.nom);
                parrain.setPrenom(users.prenom);
                parrain.setPays(users.pays);
                parrain.setUser(user);
                parrainRepository.save(parrain);

                return ResponseEntity.ok("Parrain créé avec succès !");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(keycloakService.error);
            }

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la création de Parrain : " + e.getMessage());
        }

    }
}
