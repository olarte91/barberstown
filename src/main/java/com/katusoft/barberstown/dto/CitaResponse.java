package com.katusoft.barberstown.dto;

import com.katusoft.barberstown.enums.EstadoCita;
import com.katusoft.barberstown.model.Barbero;
import com.katusoft.barberstown.model.Cliente;
import com.katusoft.barberstown.model.Servicio;
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
  private Barbero barbero;
  private Cliente cliente;
  private Servicio servicio;
  private LocalDateTime fechaHora;
  private EstadoCita estadoCita;
  private double valor;
}
