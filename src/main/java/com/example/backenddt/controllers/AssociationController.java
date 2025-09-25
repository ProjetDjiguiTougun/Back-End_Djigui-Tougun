package com.example.backenddt.controllers;

import com.example.backenddt.entites.Association;
import com.example.backenddt.entites.Enfant;
import com.example.backenddt.entites.User;
import com.example.backenddt.repository.AssociationRepository;
import com.example.backenddt.repository.UserRepository;
import com.example.backenddt.requeteDTO.BesoinRequestDTO;
import com.example.backenddt.requeteDTO.CampagneRequestDTO;
import com.example.backenddt.requeteDTO.EnfantRequestDTO;
import com.example.backenddt.services.BesoinService;
import com.example.backenddt.services.CampagneService;
import com.example.backenddt.services.EnfantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/association")
public class AssociationController {
    @Autowired
    private AssociationRepository associationRepository;

    @Autowired
    private CampagneService campagneService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnfantService enfantService;

    @Autowired
    private BesoinService besoinService;

    public Association getUser(Jwt jwt){
//        @AuthenticationPrincipal
        User user = userRepository.findByEmail(jwt.getClaim("email"));
        try {
            return associationRepository.findByUser(user);
        } catch (Exception e) {
            return null;
        }
    }
    @GetMapping
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Bienvenue a vous Admin de l'Association");
    }

    //Pour lister les informations d'une association:
    @GetMapping("/infos")
    public Map<String,Object> informations(@AuthenticationPrincipal Jwt jwt){
        Association asso = getUser(jwt);
        if (asso != null){
            return Map.of(
                    "nom de l'association",asso.getNomAssociation(),
                    "nom du Directeur",asso.getNomDirecteur(),
                    "Prenom du Directeur",asso.getPrenomDirecteur()
            );
        }
        return Map.of(
                "Erreur","Veillez vous connecter !!!"
        );
    }

    //Pour la creation des Campagnes:
    @PostMapping("/createCampagne")
    public ResponseEntity<?> createCampagne(@RequestBody CampagneRequestDTO campagneRequest, @AuthenticationPrincipal Jwt jwt){
        Association asso = getUser(jwt);
        if (asso == null) {
            return ResponseEntity.status(401).body("Vous n'etes pas connecter");
        }
        return campagneService.createCampagne(campagneRequest,asso);
    }

    //Pour la liste des Campagnes :
    @GetMapping("/campagnes")
    public ResponseEntity<?> listCampagne(@AuthenticationPrincipal Jwt jwt){
        Association asso = getUser(jwt);
        if (asso == null) {
            return ResponseEntity.status(401).body("Vous n'etes pas connecter");
        }
        return campagneService.listCampagne(asso);
    }

    //Pour l'ajout des Besoins dans la table Besoin :
    @PostMapping("/createBesoin")
    public ResponseEntity<?> createBesoin(@RequestBody BesoinRequestDTO besoin){
        return besoinService.newBesoin(besoin);
    }

    //Pour l'insertion d'un Enfant :
    @PostMapping("/createEnfant")
    public ResponseEntity<?> createEnfant(@RequestBody EnfantRequestDTO enfantRequestDTO,@AuthenticationPrincipal Jwt jwt){
        Association asso = getUser(jwt);
        Enfant enfantCreate = enfantService.createEnfant(enfantRequestDTO,asso);
        if (asso == null) {
            return ResponseEntity.status(401).body("Vous n'etes pas connecter");
        }
        if (enfantCreate!=null){
            return ResponseEntity.ok().body(enfantCreate);
        }
        return ResponseEntity.ofNullable("Erreur lors de la creation d'un enfant");
    }
}
