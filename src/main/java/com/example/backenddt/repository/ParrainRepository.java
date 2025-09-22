package com.example.backenddt.repository;

import com.example.backenddt.entites.Parrain;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ParrainRepository extends JpaRepository<Parrain,Long> {
}
