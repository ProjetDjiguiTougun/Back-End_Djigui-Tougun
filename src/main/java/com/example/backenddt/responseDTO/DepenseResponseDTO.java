package com.example.backenddt.responseDTO;

import com.example.backenddt.enumerations.Categorie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DepenseResponseDTO {
    private Long id;

    private int montant;

    private String urlJustificatif;

    private Categorie categorieDepense;

    private String description;
}
