package com.example.backenddt.repository;

import com.example.backenddt.entites.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentRepository extends JpaRepository<Parent,Long> {
    Optional<Parent> findByTelephone(String telephone);
}
