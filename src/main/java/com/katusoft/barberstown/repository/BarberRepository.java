package com.katusoft.barberstown.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.katusoft.barberstown.model.Barber;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BarberRepository extends JpaRepository<Barber, UUID>{
}
