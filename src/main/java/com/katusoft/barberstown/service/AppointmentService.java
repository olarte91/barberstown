package com.katusoft.barberstown.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.katusoft.barberstown.dto.AppointmentRequest;
import com.katusoft.barberstown.dto.AppointmentResponse;
import com.katusoft.barberstown.exception.BarberoNoEncontradoException;
import com.katusoft.barberstown.exception.HorarioNoDisponibleException;
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
  private final AppointmentRepository appointmentRepository;
  private final BarberRepository barberRepository;
  private final ServicioRepository servicioRepository;
  private final CustomerRepository customerRepository;


  //Crear una nueva cita
  public AppointmentResponse createAppointment(AppointmentRequest appointmentRequest) {

  if (!isTimeSlotAvailable(appointmentRequest.getFechaHora(), appointmentRequest.getBarberoId())) {

      throw new HorarioNoDisponibleException("El horario no está disponible para este barbero");
    }

    Barber barber = barberRepository.findById(appointmentRequest.getBarberoId())
        .orElseThrow(() -> new BarberoNoEncontradoException("No se encontró el barbero"));

    Customer customer = customerRepository.findById(appointmentRequest.getClienteId())
        .orElseThrow(() -> new BarberoNoEncontradoException("No se encontró el cliente"));

    Service service = servicioRepository.findById(appointmentRequest.getServicioId())
        .orElseThrow(() -> new ServicioNoEncontradoException("No se encontró el servicio"));

    Appointment newAppointment = Appointment.builder()
        .barber(barber)
        .customer(customer)
        .service(service)
        .dateTime(appointmentRequest.getFechaHora())
        .valor(appointmentRequest.getValor())
        .estado(appointmentRequest.getEstado())
        .build();

    appointmentRepository.save(newAppointment);

    return this.toDto(newAppointment);

  }

  public List<Appointment> obtenerCitas() {
    return appointmentRepository.findAll();
  }

  private AppointmentResponse toDto(Appointment appointment) {
    return new AppointmentResponse().builder()
        .barber(appointment.getBarber())
        .customer(appointment.getCustomer())
        .service(appointment.getService())
        .valor(appointment.getValor())
        .fechaHora(appointment.getDateTime())
        .estadoCita(appointment.getEstado())
        .build();
  }

  public boolean isTimeSlotAvailable(LocalDateTime dateTime, UUID barberId) {
    return !appointmentRepository.findByBarberIdAndDateTime(dateTime, barberId);
  }


}
