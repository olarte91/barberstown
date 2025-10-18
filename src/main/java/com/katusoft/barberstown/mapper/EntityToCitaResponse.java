package com.katusoft.barberstown.mapper;

import com.katusoft.barberstown.dto.CitaResponse;
import com.katusoft.barberstown.model.Cita;

public class EntityToCitaResponse {

  public static CitaResponse toCitaResponse(Cita cita){
    return new CitaResponse().builder()
        .barbero(cita.getBarbero())
        .cliente(cita.getCliente())
        .servicio(cita.getServicio())
        .valor(cita.getValor())
        .fechaHora(cita.getFechaHora())
        .estadoCita(cita.getEstado())
        .build();
  }
}
