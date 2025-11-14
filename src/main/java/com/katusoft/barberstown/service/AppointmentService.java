package com.katusoft.barberstown.service;

import java.util.List;

import com.katusoft.barberstown.dto.CitaRequest;
import com.katusoft.barberstown.dto.CitaResponse;
import com.katusoft.barberstown.exception.BarberoNoEncontradoException;
import com.katusoft.barberstown.exception.ServicioNoEncontradoException;
import com.katusoft.barberstown.model.Appointment;
import com.katusoft.barberstown.model.Barber;
import com.katusoft.barberstown.model.Customer;
import com.katusoft.barberstown.model.Service;
import com.katusoft.barberstown.repository.BarberRepository;
import com.katusoft.barberstown.repository.CustomerRepository;
import com.katusoft.barberstown.repository.ServicioRepository;
import lombok.AllArgsConstructor;

import com.katusoft.barberstown.repository.AppointmentRepository;


@org.springframework.stereotype.Service
@AllArgsConstructor
public class AppointmentService {
    private  final AppointmentRepository appointmentRepository;
    private  final BarberRepository barberRepository;
    private  final ServicioRepository servicioRepository;
    private  final CustomerRepository customerRepository;


    //Crear una nueva cita
    public CitaResponse crearCita(CitaRequest citaRequest){

      Barber barber = barberRepository.findById(citaRequest.getBarberoId())
          .orElseThrow( () -> new BarberoNoEncontradoException("No se encontró el barbero"));

      Customer customer = customerRepository.findById(citaRequest.getClienteId())
          .orElseThrow( () -> new BarberoNoEncontradoException("No se encontró el cliente"));

      Service service = servicioRepository.findById(citaRequest.getServicioId())
          .orElseThrow( () -> new ServicioNoEncontradoException("No se encontró el servicio"));

        Appointment newAppointment = Appointment.builder()
            .barber(barber)
            .customer(customer)
            .service(service)
            .fechaHora(citaRequest.getFechaHora())
            .valor(citaRequest.getValor())
            .estado(citaRequest.getEstado())
            .build();

      appointmentRepository.save(newAppointment);

        return this.toDto(newAppointment);
    }

    public List<Appointment> obtenerCitas(){
        return appointmentRepository.findAll();
    }

  private CitaResponse toDto(Appointment appointment){
    return new CitaResponse().builder()
        .barber(appointment.getBarber())
        .customer(appointment.getCustomer())
        .service(appointment.getService())
        .valor(appointment.getValor())
        .fechaHora(appointment.getFechaHora())
        .estadoCita(appointment.getEstado())
        .build();
  }

}
