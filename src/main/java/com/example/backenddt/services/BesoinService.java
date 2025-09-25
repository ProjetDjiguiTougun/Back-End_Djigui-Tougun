package com.example.backenddt.services;

import com.example.backenddt.entites.Besoin;
import com.example.backenddt.entites.Campagne;
import com.example.backenddt.repository.BesoinRepository;
import com.example.backenddt.repository.CampagneRepository;
import com.example.backenddt.requeteDTO.BesoinRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BesoinService {
    @Autowired
    private BesoinRepository besoinRepository;
    @Autowired
    private CampagneRepository campagneRepository;

    public ResponseEntity<?> newBesoin(BesoinRequestDTO besoin){
        Optional<Campagne> campagne = campagneRepository.findById(besoin.campagne_id);
        if (campagne!=null){
            Besoin b = new Besoin();
            b.setDetail(besoin.detail);
            b.setCampagne(campagne.get());
            b.setMontant(besoin.montant);
            besoinRepository.save(b);
            return ResponseEntity.ok().body(b);
        }
        return ResponseEntity.ofNullable("Erreur lors de l'insertion de besoin .");
    }
}
