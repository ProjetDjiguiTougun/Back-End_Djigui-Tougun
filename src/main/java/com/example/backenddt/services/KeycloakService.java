package com.example.backenddt.services;

import com.example.backenddt.enumerations.Role;
import jakarta.ws.rs.core.Response;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class KeycloakService {
    private final Keycloak keycloak;

    public String error;

    public KeycloakService() {
        this.keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8080/")
                .realm("master")
                .clientId("admin-cli")
                .username("admin")
                .password("admin")
                .grantType(OAuth2Constants.PASSWORD)
                .build();
    }

    public Boolean createUserWithRole(String nom, String prenom, String email, String password, Role roleName){
        UserRepresentation user = new  UserRepresentation();
        user.setUsername(email);
        user.setEmail(email);
        user.setFirstName(nom);
        user.setLastName(prenom);
        user.setEnabled(true);


        String realm = "skypper";
        Response resp =  keycloak.realm(realm).users().create(user);

        if (resp.getStatus() == 201 ){

            //Pour la recuperation de l'id User creer
            String userId = resp.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

            //Pour ajout de mot de passe au compte de l'utisateur :
            CredentialRepresentation credential = new CredentialRepresentation();

            credential.setType(CredentialRepresentation.PASSWORD);
            credential.setValue(password);
            credential.setTemporary(false);

            //Enregistrement de mot de passe
            keycloak.realm(realm).users().get(userId).resetPassword(credential);

            //On recuperer la liste des roles dans notre realms:
            RoleRepresentation role = keycloak.realm(realm).roles().get(String.valueOf(roleName)).toRepresentation();

            //Enregistrement de Role dans la base de donnee Keycloak
            keycloak.realm(realm).users().get(userId).roles().realmLevel().add(Collections.singletonList(role));

            return true;

        } else if (resp.getStatus() == 409) {
            error =  "Conflit : l’utilisateur " + nom + " "+prenom+ " ou l’email " + email + " existe déjà.";
            return false;
        } else {
            error =  "Erreur (" + resp.getStatus() + ") : " + resp.getStatusInfo();
            return false;
        }
    }
}
