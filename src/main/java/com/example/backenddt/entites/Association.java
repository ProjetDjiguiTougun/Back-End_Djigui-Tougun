package com.example.backenddt.entites;

import com.example.backenddt.enumerations.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "association")
public class Association {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "utilisateur_id")
    private User user;

    @Column
    private String prenomDirecteur;

    @Column
    private String nomDirecteur;

    @Column
    private String emailDirecteur;

    @Column
    private String passwordDirecteur;

    @Column
    private String nomAssociation;

    @Column
    private String adresse;

    @Column
    private String description;

    @OneToMany(mappedBy = "association", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Campagne> campagne = new HashSet<>();

}
