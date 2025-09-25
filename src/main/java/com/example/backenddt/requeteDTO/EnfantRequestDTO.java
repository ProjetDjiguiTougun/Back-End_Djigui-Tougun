package com.example.backenddt.requeteDTO;

import com.example.backenddt.enumerations.Genre;
import com.example.backenddt.enumerations.Role;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
public class EnfantRequestDTO {
    public Date date_naissance;
    public String niveau;
    public String nom;
    public String prenom;
    public List<Long> besoin_id;
    public Long campagne_id;
    public Genre genre;
    public ParentRequestDTO parent;
}
