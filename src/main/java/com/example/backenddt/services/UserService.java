package com.example.backenddt.services;

import com.example.backenddt.entites.User;
import com.example.backenddt.enumerations.Role;
import com.example.backenddt.repository.UserRepository;
import com.example.backenddt.requeteDTO.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Service
public class UserService{
    private final KeycloakService keycloakService;
    @Autowired
    private UserRepository userRepository;

    public UserService(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }

    //Pour la creation d'utilisateur :
    public ResponseEntity<?> createUser(UserRequestDTO users){
        switch (users.getRole()){
            case ADMIN -> {
                try {

                    boolean create = keycloakService.createUserWithRole(users.getNomComplet(), "", users.getEmail(), users.getPassword(),users.getRole());
                    if (create){
                        User user = new User();
                        user.setEmail(users.getEmail());
                        user.setPassword(users.getPassword());
                        user.setRole(users.getRole());
                        userRepository.save(user);
                        return ResponseEntity.ok("Admin Creer avec succes !!!");
                    }
                    return ResponseEntity.ok("Erreur cote keycloak !!!");
                } catch (RuntimeException e) {
                    throw new RuntimeException(e);
                }
            }
            case PARENT -> {
                try {
                    boolean create = keycloakService.createUserWithRole(users.getNomComplet(), "", users.getEmail(), users.getPassword(),users.getRole());
                    if (create){
                        User user = new User();
                        user.setEmail(users.getEmail());
                        user.setPassword(users.getPassword());
                        user.setRole(users.getRole());
                        userRepository.save(user);
                        return ResponseEntity.ok("Parent Creer avec succes !!!");
                    }
                    return ResponseEntity.ok("Erreur cote keycloak !!!");
                } catch (RuntimeException e) {
                    return ResponseEntity.ofNullable("Erreur lors de la creation de Parent");
                }
            }
            case PARRAIN -> {
                try {
                    boolean result = keycloakService.createUserWithRole(
                            users.getNomComplet(),
                            users.getPrenom(),
                            users.getEmail(),
                            users.getPassword(),
                            users.getRole()
                    );

                    if (result) {
                        User user = new User();
                        user.setEmail(users.getEmail());
                        user.setPassword(users.getPassword());
                        user.setRole(users.getRole());
                        userRepository.save(user);

                        return ResponseEntity.ok("Parrain créé avec succès !");
                    } else {
                        return ResponseEntity.status(HttpStatus.CONFLICT).body(keycloakService.error);
                    }

                } catch (RuntimeException e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Erreur lors de la création de Parrain : " + e.getMessage());
                }


            }
            case ASSOCIATION -> {

                try {
                    boolean create = keycloakService.createUserWithRole(users.getNomComplet(), "", users.getEmail(), users.getPassword(),users.getRole());
                    if (create){
                        User user = new User();
                        user.setEmail(users.getEmail());
                        user.setPassword(users.getPassword());
                        user.setRole(users.getRole());
                        userRepository.save(user);
                        return ResponseEntity.ok("Association Creer avec succes !!!");
                    }
                    return ResponseEntity.ok("Erreur cote keycloak !!!");
                } catch (RuntimeException e) {
                    return ResponseEntity.ofNullable("Erreur lors de la creation de L'Association");
                }
            }
            default -> {
                return ResponseEntity.badRequest().body("Erreur inattendue!!!!");
            }
        }
    }
}
