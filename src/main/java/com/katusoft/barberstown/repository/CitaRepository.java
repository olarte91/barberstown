package com.katusoft.barberstown.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.katusoft.barberstown.enums.EstadoCita;
import com.katusoft.barberstown.model.Cita;
import java.time.LocalDate;
import java.util.UUID;


public interface CitaRepository extends JpaRepository<Cita, UUID>{

    List<Cita> findByBarberoId(UUID barberoId);
    List<Cita> findByClienteId(UUID clienteId);
    List<Cita> findByFecha(LocalDate fecha);
    List<Cita> findBybarberoIdAndFecha(UUID id, LocalDate fecha);
    List<Cita> findByEstado(EstadoCita estado);

}
