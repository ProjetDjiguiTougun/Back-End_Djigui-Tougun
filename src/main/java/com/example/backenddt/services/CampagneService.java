package com.example.backenddt.services;

import com.example.backenddt.entites.Association;
import com.example.backenddt.entites.Campagne;
import com.example.backenddt.mappers.CampagneMapper;
import com.example.backenddt.repository.AssociationRepository;
import com.example.backenddt.repository.CampagneRepository;
import com.example.backenddt.requeteDTO.CampagneRequestDTO;
import com.example.backenddt.responseDTO.ListCampagneResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampagneService {
    @Autowired
    private CampagneRepository campagneRepository;

    @Autowired
    private AssociationRepository associationRepository;

    public ResponseEntity<?> createCampagne(CampagneRequestDTO campagneRequestDTO, Association association){
        Campagne campagne = new Campagne();
        try {
            campagne.setNom(campagneRequestDTO.nom);
            campagne.setAnnee(campagneRequestDTO.annee);
            campagne.setDate_debut(campagneRequestDTO.date_debut);
            campagne.setDate_fin(campagneRequestDTO.date_fin);
            campagne.setAssociation(association);
            campagneRepository.save(campagne);
            return ResponseEntity.ok().body("Campagne creer avec succes !!!");
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body("Erreur lors de la creation de campagne");
        }
    }

    public ResponseEntity<?> listCampagne(Association asso){
        try {

            List<Campagne> campagneResponse = campagneRepository.findByAssociation(asso);
            List<ListCampagneResponse> resp = new CampagneMapper().listCampagneToDto(campagneResponse);

            return ResponseEntity.ok().body(resp);

        } catch (Exception e) {
//            throw new RuntimeException(e);
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
