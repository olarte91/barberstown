package com.katusoft.barberstown.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.katusoft.barberstown.model.Servicio;

import java.util.UUID;

public interface ServicioRepository extends JpaRepository<Servicio, UUID>{

}
