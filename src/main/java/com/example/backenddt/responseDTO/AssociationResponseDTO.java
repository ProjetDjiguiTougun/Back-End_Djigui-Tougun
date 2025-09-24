package com.example.backenddt.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AssociationResponseDTO {
    private String prenomDirecteur;

    private String nomDirecteur;

    private String emailDirecteur;

    private String passwordDirecteur;

    private String nomAssociation;

    private String adresse;

    private String description;
}
