package com.example.backenddt.repository;

import com.example.backenddt.entites.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur,Long> {

}
