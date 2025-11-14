package com.katusoft.barberstown.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.katusoft.barberstown.enums.EstadoCita;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "citas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "barbero_id")
    private Barber barber;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Service service;

    @Column(nullable = false, name = "fecha_hora")
    private LocalDateTime fechaHora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "estado_cita")
    private EstadoCita estado;

    @Column(nullable = false)
    private double valor;

}
