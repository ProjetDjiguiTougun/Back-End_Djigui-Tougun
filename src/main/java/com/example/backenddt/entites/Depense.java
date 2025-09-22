package com.example.backenddt.entites;

import com.example.backenddt.enumerations.Categorie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Depense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int montant;

    @Column
    private String urlJustificatif;

    @Column
    @Enumerated(EnumType.STRING)
    private Categorie categorieDepense;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campagne_id")
    private Campagne campagne;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enfant_id")
    private Enfant enfant;

}
