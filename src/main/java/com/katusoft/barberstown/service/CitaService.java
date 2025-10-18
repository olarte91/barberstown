package com.katusoft.barberstown.service;

import java.util.List;

import com.katusoft.barberstown.dto.CitaRequest;
import com.katusoft.barberstown.exception.BarberoNoEncontradoException;
import com.katusoft.barberstown.exception.ServicioNoEncontradoException;
import com.katusoft.barberstown.model.Cliente;
import com.katusoft.barberstown.model.Servicio;
import com.katusoft.barberstown.repository.BarberoRepository;
import com.katusoft.barberstown.repository.ClienteRepository;
import com.katusoft.barberstown.repository.ServicioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.katusoft.barberstown.model.Barbero;
import com.katusoft.barberstown.model.Cita;
import com.katusoft.barberstown.repository.CitaRepository;


@Service
@AllArgsConstructor
public class CitaService {
    private  final CitaRepository citaRepository;
    private  final BarberoRepository barberoRepository;
    private  final ServicioRepository servicioRepository;
    private  final ClienteRepository clienteRepository;


    //Crear una nueva cita
    public Cita crearCita(CitaRequest citaRequest){

      Barbero barbero = barberoRepository.findById(citaRequest.getBarberoId())
          .orElseThrow( () -> new BarberoNoEncontradoException("No se encontró el barbero"));

      Cliente cliente = clienteRepository.findById(citaRequest.getClienteId())
          .orElseThrow( () -> new BarberoNoEncontradoException("No se encontró el cliente"));

      Servicio servicio = servicioRepository.findById(citaRequest.getServicioId())
          .orElseThrow( () -> new ServicioNoEncontradoException("No se encontró el servicio"));

        Cita nuevaCita = Cita.builder()
            .barbero(barbero)
            .cliente(cliente)
            .servicio(servicio)
            .fechaHora(citaRequest.getFechaHora())
            .valor(citaRequest.getValor())
            .estado(citaRequest.getEstado())
            .build();

        return citaRepository.save(nuevaCita);
    }

    public List<Cita> obtenerCitas(){
        return citaRepository.findAll();
    }

}
