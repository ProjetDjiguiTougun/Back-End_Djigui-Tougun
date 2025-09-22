package com.example.backenddt.entites;

import com.example.backenddt.enumerations.PaiementEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int montant;

    @Enumerated(EnumType.STRING)
    @Column
    private PaiementEnum methodePaiement;

    @Column
    private Date datePaiement;

    @Column
    private String reference;

    @Column
    private Boolean statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parrain_id")
    private Parrain parrain;

}
