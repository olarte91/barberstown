package com.katusoft.barberstown.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.katusoft.barberstown.enums.EstadoCita;

public class CitaRequest {
    private Long clienteId;
    private Long barberoId;
    private Long servicioId;
    private LocalDate fecha;
    private LocalTime hora;
    private double valor;
    private EstadoCita estado;

    public EstadoCita getEstado() {
        return estado;
    }
    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }
    public LocalTime getHora() {
        return hora;
    }
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    public Long getClienteId() {
        return clienteId;
    }
    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
    public Long getBarberoId() {
        return barberoId;
    }
    public void setBarberoId(Long barberoId) {
        this.barberoId = barberoId;
    }
    public Long getServicioId() {
        return servicioId;
    }
    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }

    
    
}
