package com.example.backenddt.services;

import com.example.backenddt.entites.*;
import com.example.backenddt.repository.BesoinRepository;
import com.example.backenddt.repository.CampagneRepository;
import com.example.backenddt.repository.EnfantRepository;
import com.example.backenddt.repository.ParentRepository;
import com.example.backenddt.requeteDTO.EnfantRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnfantService {
    @Autowired
    private ParentService parentService;
    @Autowired
    private EnfantRepository enfantRepository;

    @Autowired
    private BesoinRepository besoinRepository;

    @Autowired
    private CampagneRepository campagneRepository;

    @Autowired
    private ParentRepository parentRepository;

    public Enfant createEnfant(EnfantRequestDTO enfantRequestDTO, Association asso) {
        String numero = enfantRequestDTO.parent.telephone;
        List<Long> besoins = enfantRequestDTO.besoin_id;
        Optional<Parent> parentVerif = parentRepository.findByTelephone(numero);
        Parent parent;
        parent = parentVerif.orElseGet(() -> parentService.createParent(enfantRequestDTO.parent));
        try {
            Enfant enfant = new Enfant();
            enfant.setNom(enfantRequestDTO.nom);
            enfant.setPrenom(enfantRequestDTO.prenom);
            enfant.setNiveau(enfantRequestDTO.niveau);
            enfant.setGenre(enfantRequestDTO.genre);
            enfant.setParent(parent);
            enfant.setDateNaissance(enfantRequestDTO.date_naissance);
            enfantRepository.save(enfant);
            //Pour lajouter dans la table intermediaire appartenance :

            Optional<Campagne> campagne = campagneRepository.findById(enfantRequestDTO.campagne_id);
            campagne.ifPresent(cmp -> cmp.getEnfant().add(enfant));

            //Pour parcourir la liste des besoins de l'enfant et linserer dans la table intermediaire
            for (Long b : besoins) {
                Optional<Besoin> optionalBesoin = besoinRepository.findById(b);

                if (optionalBesoin.isPresent()) {
                    Besoin besoin = optionalBesoin.get();

                    // Ajouter l'enfant à l'ensemble de besoins
                    besoin.getEnfant().add(enfant);

                    // Sauvegarder l'entité Besoin. JPA gérera l'insertion dans la table de jointure.
                    besoinRepository.save(besoin);
                }
            }
            return enfant;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
