package com.katusoft.barberstown.dto;

import com.katusoft.barberstown.enums.EstadoCita;
import com.katusoft.barberstown.model.Barber;
import com.katusoft.barberstown.model.Customer;
import com.katusoft.barberstown.model.Service;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CitaResponse {
  private Barber barber;
  private Customer customer;
  private Service service;
  private LocalDateTime fechaHora;
  private EstadoCita estadoCita;
  private double valor;
}
