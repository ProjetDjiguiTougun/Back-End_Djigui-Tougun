package com.example.backenddt.services;

import com.example.backenddt.entites.Parent;
import com.example.backenddt.entites.Parrain;
import com.example.backenddt.entites.User;
import com.example.backenddt.repository.ParentRepository;
import com.example.backenddt.repository.UserRepository;
import com.example.backenddt.requeteDTO.ParentRequestDTO;
import com.example.backenddt.requeteDTO.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ParentService {
    private final KeycloakService keycloakService;
    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private UserRepository userRepository;

    public ParentService(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }

    public Parent createParent(ParentRequestDTO users) {
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

                Parent parent = new Parent();
                parent.setAdresse(users.adresse);
                parent.setNom(users.nom);
                parent.setPrenom(users.prenom);
                parent.setTelephone(users.telephone);
                parent.setUser(user);
                parentRepository.save(parent);

                return parent;
            } else {
                return null;
            }

        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Erreur lors de la création de Parent : " + e.getMessage());
            return null;
        }
    }
}
