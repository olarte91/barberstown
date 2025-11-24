package com.katusoft.barberstown.repository;

import com.katusoft.barberstown.model.Appointment;
import com.katusoft.barberstown.model.Barber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

  boolean existsByDateTime(LocalDateTime date);
  boolean findByBarberIdAndDateTime(LocalDateTime dateTime, UUID barberId);

}
