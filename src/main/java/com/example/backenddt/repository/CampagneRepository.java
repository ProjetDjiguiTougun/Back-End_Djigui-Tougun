package com.example.backenddt.repository;

import com.example.backenddt.entites.Association;
import com.example.backenddt.entites.Campagne;
import com.example.backenddt.responseDTO.ListCampagneResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampagneRepository extends JpaRepository<Campagne,Long> {
    List<Campagne> findByAssociation(Association association);
}
