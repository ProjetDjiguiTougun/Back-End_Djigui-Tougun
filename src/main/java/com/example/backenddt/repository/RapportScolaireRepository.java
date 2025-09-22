package com.example.backenddt.repository;

import com.example.backenddt.entites.RapportScolaire;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RapportScolaireRepository extends JpaRepository<RapportScolaire,Long> {
}
