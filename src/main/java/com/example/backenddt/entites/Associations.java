package com.example.backenddt.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "associations")
public class Associations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
