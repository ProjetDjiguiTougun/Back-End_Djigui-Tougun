package com.example.backenddt.responseDTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class EnfantResponseDTO {
    private Long id;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String matricule;
    private String niveau;
}
