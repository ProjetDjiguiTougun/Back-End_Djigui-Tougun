package com.example.backenddt.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ListCampagneResponse {
    private Long id;
    private String nom;
    private Date annee;
    private Date date_debut;
    private Date date_fin;
    private List<EnfantResponseDTO> enfants;
    private List<DepenseResponseDTO> depenses;
}
