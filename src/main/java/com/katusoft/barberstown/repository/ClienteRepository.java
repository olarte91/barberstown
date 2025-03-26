package com.katusoft.barberstown.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.katusoft.barberstown.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
