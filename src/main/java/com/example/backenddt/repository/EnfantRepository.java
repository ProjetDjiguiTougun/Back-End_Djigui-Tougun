package com.example.backenddt.repository;

import com.example.backenddt.entites.Enfant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnfantRepository extends JpaRepository<Enfant,Long> {
}
