package com.example.backenddt.mappers;

import com.example.backenddt.entites.Campagne;
import com.example.backenddt.entites.Depense;
import com.example.backenddt.entites.Enfant;
import com.example.backenddt.repository.CampagneRepository;
import com.example.backenddt.responseDTO.DepenseResponseDTO;
import com.example.backenddt.responseDTO.EnfantResponseDTO;
import com.example.backenddt.responseDTO.ListCampagneResponse;

import java.util.List;
import java.util.stream.Collectors;

public class CampagneMapper {
    public CampagneMapper(){}

    public List<ListCampagneResponse> listCampagneToDto(List<Campagne> list){

        return list.stream()
                .map(this::ToDto)
                .collect(Collectors.toList());
    }

    private ListCampagneResponse ToDto(Campagne campagne) {
        List<EnfantResponseDTO> enfants = campagne.getEnfant().stream()
                .map(this::convertToEnfantDto)
                .collect(Collectors.toList());

        List<DepenseResponseDTO> depenses = campagne.getDepense().stream()
                .map(this::convertToDepenseDto)
                .collect(Collectors.toList());

        return new ListCampagneResponse(
                campagne.getId(),
                campagne.getNom(),
                campagne.getDate_debut(),
                campagne.getDate_fin(),
                campagne.getAnnee(),
                enfants,
                depenses
        );
    }

    private EnfantResponseDTO convertToEnfantDto(Enfant enfant) {
        return new EnfantResponseDTO(
                enfant.getId(),
                enfant.getNom(),
                enfant.getPrenom(),
                enfant.getDateNaissance(),
                enfant.getMatricule(),
                enfant.getNiveau()
        );
    }

    private DepenseResponseDTO convertToDepenseDto(Depense depense) {
        return new DepenseResponseDTO(
                depense.getId(),
                depense.getMontant(),
                depense.getUrlJustificatif(),
                depense.getCategorieDepense(),
                depense.getDescription()
        );
    }
}
