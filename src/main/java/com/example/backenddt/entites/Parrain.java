package com.example.backenddt.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Parrain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "utilisateur_id")
    private User user;

    @Column
    private String nom;

    @Column
    private String prenom;

    @Column
    private String adresse;

    @Column
    private String pays;


    @OneToMany(mappedBy = "parrain", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Parrainage> parrainages = new HashSet<>();

    @OneToMany(mappedBy = "parrain", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Paiement> paiements = new HashSet<>();

    @OneToMany(mappedBy = "parrain", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Notification> notifications = new HashSet<>();



}
