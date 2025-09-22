package com.example.backenddt.repository;

import com.example.backenddt.entites.Campagne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampagneRepository extends JpaRepository<Campagne,Long> {
}
