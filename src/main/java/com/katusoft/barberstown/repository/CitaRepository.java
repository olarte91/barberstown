package com.katusoft.barberstown.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.katusoft.barberstown.enums.EstadoCita;
import com.katusoft.barberstown.model.Cita;
import java.time.LocalDate;


public interface CitaRepository extends JpaRepository<Cita, Long>{

    List<Cita> findByBarberoId(Long barberoId);
    List<Cita> findByClienteId(Long clienteId);
    List<Cita> findByFecha(LocalDate fecha);
    List<Cita> findBybarberoIdAndFecha(Long id, LocalDate fecha);
    List<Cita> findByEstado(EstadoCita estado);

}
