package com.example.backenddt.entites;

import com.example.backenddt.enumerations.Statut;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
@Entity
public class Parrainage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date date_debut;

    @Column
    private Date date_fin;

    @Column
    private Statut statut;

    @Column
    private int montantTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parrain_id")
    private Parrain parrain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enfant_id")
    private Enfant enfant;
}
