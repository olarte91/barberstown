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
import com.katusoft.barberstown.exception.BarberoNoEncontradoException;
import com.katusoft.barberstown.exception.ClienteNoEncontradoException;
import com.katusoft.barberstown.exception.ServicioNoEncontradoException;

@Service
public class CitaService {
    private final CitaRepository citaRepository;
    private final BarberoRepository barberoRepository;
    private final ClienteRepository clienteRepository;
    private final ServicioRepository servicioRepository;

    public CitaService(CitaRepository citaRepository, BarberoRepository barberoRepository, ClienteRepository clienteRepository, ServicioRepository servicioRepository){
        this.citaRepository = citaRepository;
        this.barberoRepository = barberoRepository;
        this.clienteRepository = clienteRepository;
        this.servicioRepository = servicioRepository;
    }

    //Crear una nueva cita
    public Cita crearCita(CitaRequest citaRequest){
        Barbero barbero = barberoRepository.findById(citaRequest.getBarberoId())
        .orElseThrow(() -> new BarberoNoEncontradoException(citaRequest.getBarberoId()));

        Cliente cliente = clienteRepository.findById(citaRequest.getClienteId())
        .orElseThrow(() -> new ClienteNoEncontradoException(citaRequest.getClienteId()));

        Servicio servicio = servicioRepository.findById(citaRequest.getServicioId())
        .orElseThrow(() -> new ServicioNoEncontradoException(citaRequest.getServicioId()));

        Cita cita = new Cita();
        cita.setBarbero(barbero);
        cita.setCliente(cliente);
        cita.setServicio(servicio);
        cita.setFecha(citaRequest.getFecha());
        cita.setHora(citaRequest.getHora());
        cita.setValor(citaRequest.getValor());
        cita.setEstado(citaRequest.getEstado());

        return citaRepository.save(cita);
    }

    public List<Cita> obtenerCitas(){
        return citaRepository.findAll();
    }

}
