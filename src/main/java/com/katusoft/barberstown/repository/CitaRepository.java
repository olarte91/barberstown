package com.katusoft.barberstown.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.katusoft.barberstown.model.Cita;
import java.util.UUID;


public interface CitaRepository extends JpaRepository<Cita, UUID>{

}
