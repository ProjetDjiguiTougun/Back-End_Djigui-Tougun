package com.example.backenddt.repository;

import com.example.backenddt.entites.Parrainage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParrainageRepository extends JpaRepository<Parrainage,Long> {
}
