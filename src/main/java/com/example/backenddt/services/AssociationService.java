package com.example.backenddt.services;

import com.example.backenddt.entites.Association;
import com.example.backenddt.entites.Parrain;
import com.example.backenddt.entites.User;
import com.example.backenddt.repository.AssociationRepository;
import com.example.backenddt.repository.UserRepository;
import com.example.backenddt.requeteDTO.AssociationRquestDTO;
import com.example.backenddt.requeteDTO.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AssociationService {
    private final KeycloakService keycloakService;
    @Autowired
    private AssociationRepository associationRepository;
    @Autowired
    private UserRepository userRepository;

    public AssociationService(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }

    public ResponseEntity<?> createAssociation(AssociationRquestDTO users) {
        try {
            boolean result = keycloakService.createUserWithRole(
                    users.nomDirecteur,
                    users.prenomDirecteur,
                    users.emailDirecteur,
                    users.passwordDirecteur,
                    users.role
            );

            if (result) {
                User user = new User();
                user.setEmail(users.emailDirecteur);
                user.setPassword(users.passwordDirecteur);
                user.setRole(users.role);
                userRepository.save(user);

                Association association = new Association();
                association.setAdresse(users.adresse);
                association.setNom(users.nomAssociation);
                association.setUser(user);
                association.setDescription(users.description);
                associationRepository.save(association);

                return ResponseEntity.ok("Association créé avec succès !");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(keycloakService.error);
            }

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la création de Association : " + e.getMessage());
        }
    }
}
