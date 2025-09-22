package com.example.backenddt.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class RapportScolaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String urlRapport;

    @Column
    private Date periode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enfant_id")
    private Enfant enfant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campagne_id")
    private Campagne campagne;

}
