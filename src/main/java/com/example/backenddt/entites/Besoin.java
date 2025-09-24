package com.example.backenddt.entites;

import jakarta.persistence.*;

@Entity
public class Besoin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "campagne_id")
    private Campagne campagne;

    private String detail;
    private Long montant;
}