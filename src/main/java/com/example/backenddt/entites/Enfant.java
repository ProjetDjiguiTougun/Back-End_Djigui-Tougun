package com.example.backenddt.entites;

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

    @ManyToMany
    @JoinTable(
            name = "enfant_besoin",
            joinColumns = @JoinColumn(name = "enfant_id"),
            inverseJoinColumns = @JoinColumn(name = "besoin_id")
    )
    private Set<Besoin> besoin = new HashSet<>();

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
