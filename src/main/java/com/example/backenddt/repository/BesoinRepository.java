package com.example.backenddt.repository;

import com.example.backenddt.entites.Besoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BesoinRepository extends JpaRepository<Besoin,Long> {
}
