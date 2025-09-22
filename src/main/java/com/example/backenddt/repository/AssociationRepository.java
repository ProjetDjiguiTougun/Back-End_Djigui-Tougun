package com.example.backenddt.repository;

import com.example.backenddt.entites.Association;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociationRepository extends JpaRepository<Association,Long> {
}
