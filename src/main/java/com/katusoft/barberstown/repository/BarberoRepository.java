package com.katusoft.barberstown.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.katusoft.barberstown.model.Barbero;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BarberoRepository extends JpaRepository<Barbero, UUID>{
  boolean existsByEmail(String email);
}
