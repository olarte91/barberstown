package com.katusoft.barberstown.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.katusoft.barberstown.enums.EstadoCita;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CitaRequest {
    private UUID clienteId;
    private UUID barberoId;
    private UUID servicioId;
    private LocalDateTime fechaHora;
    private double valor;
    private EstadoCita estado;
}
