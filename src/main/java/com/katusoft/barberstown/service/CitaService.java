package com.katusoft.barberstown.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.katusoft.barberstown.dto.CitaRequest;
import com.katusoft.barberstown.model.Barbero;
import com.katusoft.barberstown.model.Cita;
import com.katusoft.barberstown.model.Cliente;
import com.katusoft.barberstown.model.Servicio;
import com.katusoft.barberstown.repository.BarberoRepository;
import com.katusoft.barberstown.repository.CitaRepository;
import com.katusoft.barberstown.repository.ClienteRepository;
import com.katusoft.barberstown.repository.ServicioRepository;

import lombok.RequiredArgsConstructor;

import com.katusoft.barberstown.exception.BarberoNoEncontradoException;
import com.katusoft.barberstown.exception.ClienteNoEncontradoException;
import com.katusoft.barberstown.exception.ServicioNoEncontradoException;

@Service
@RequiredArgsConstructor
public class CitaService {
    private final CitaRepository citaRepository;
    private final BarberoRepository barberoRepository;
    private final ClienteRepository clienteRepository;
    private final ServicioRepository servicioRepository;

    //Crear una nueva cita
    public Cita crearCita(CitaRequest citaRequest){
        Barbero barbero = getBarberById(citaRequest.getBarberoId());
        Cliente cliente = getClientById(citaRequest.getClienteId());
        Servicio servicio = getServiceById(citaRequest.getServicioId());

        Cita cita = Cita.builder()
            .barbero(barbero)
            .cliente(cliente)
            .servicio(servicio)
            .fecha(citaRequest.getFecha())
            .hora(citaRequest.getHora())
            .valor(citaRequest.getValor())
            .estado(citaRequest.getEstado())
            .build();

        return citaRepository.save(cita);
    }

    public List<Cita> obtenerCitas(){
        return citaRepository.findAll();
    }

    //Métodos privados para encapsular la lógica de obtención y manejo de excepciones

    private Barbero getBarberById(Long id){
        return barberoRepository.findById(id)
            .orElseThrow(() -> new BarberoNoEncontradoException(id));
    }

    private Cliente getClientById(Long id){
        return clienteRepository.findById(id)
            .orElseThrow(() -> new ClienteNoEncontradoException(id));
    }

    private Servicio getServiceById(Long id){
        return servicioRepository.findById(id)
            .orElseThrow(() -> new ServicioNoEncontradoException(id));
    }



}
