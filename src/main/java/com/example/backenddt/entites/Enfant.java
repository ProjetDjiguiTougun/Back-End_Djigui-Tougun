package com.example.backenddt.entites;

import com.example.backenddt.enumerations.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Entity
public class Enfant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nom;

    @Column
    private String prenom;

    @Column
    private Date dateNaissance;

    @Column
    private String matricule;

    @Column
    private String niveau;
    @Column
    private Genre genre;

    @OneToMany(mappedBy = "enfant",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<RapportScolaire> rapportScolaire = new HashSet<>();

    @OneToMany(mappedBy = "enfant",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Depense> depense = new HashSet<>();

    @OneToMany(mappedBy = "enfant",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Parrainage> parrainage = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Parent parent;

}
