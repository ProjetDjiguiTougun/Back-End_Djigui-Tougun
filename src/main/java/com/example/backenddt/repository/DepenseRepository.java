package com.example.backenddt.repository;

import com.example.backenddt.entites.Depense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepenseRepository extends JpaRepository<Depense,Long> {
}
