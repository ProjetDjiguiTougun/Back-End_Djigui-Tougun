package com.example.backenddt.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date dateEnvoi;

    @Column
    private String message;

    @Column
    private Boolean estLu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "association_id")
    private Association association;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parrain_id")
    private Parrain parrain;
}
