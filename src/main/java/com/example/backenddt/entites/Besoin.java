package com.example.backenddt.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Besoin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "campagne_id")
    private Campagne campagne;

    @Column
    private String detail;

    @Column
    private Long montant;

    @ManyToMany
    @JoinTable(
            name = "enfant_besoin",
            joinColumns = @JoinColumn(name = "besoin_id"),
            inverseJoinColumns = @JoinColumn(name = "enfant_id")
    )
    private Set<Enfant> enfant = new HashSet<>();
}