package com.katusoft.barberstown.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.katusoft.barberstown.model.Barbero;

import java.util.UUID;

public interface BarberoRepository extends JpaRepository<Barbero, UUID>{

}
