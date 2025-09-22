package com.example.backenddt.repository;

import com.example.backenddt.entites.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement,Long> {
}
