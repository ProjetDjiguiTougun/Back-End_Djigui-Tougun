package com.example.backenddt.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Campagne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nom;

    @Column
    private Date date_debut;

    @Column
    private Date date_fin;

    @Column
    private Date annee;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "appartenance",
            joinColumns = @JoinColumn(name = "campagne_id"),
            inverseJoinColumns =  @JoinColumn(name = "enfant_id")
    )
    private Set<Enfant> enfant = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "association_id")
    private Association association;

    @OneToMany(mappedBy = "campagne",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<RapportScolaire> rapportScolaire = new HashSet<>();

    @OneToMany(mappedBy = "campagne",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Depense> depense = new HashSet<>();

}
