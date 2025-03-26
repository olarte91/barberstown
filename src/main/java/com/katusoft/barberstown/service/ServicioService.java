package com.katusoft.barberstown.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.katusoft.barberstown.model.Servicio;
import com.katusoft.barberstown.repository.ServicioRepository;

@Service
public class ServicioService {
    private final ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository){
        this.servicioRepository = servicioRepository;
    }

    public List<Servicio> getAllServicios(){
        return servicioRepository.findAll();
    }

    public Servicio saveServicio(Servicio servicio){
        
        return servicioRepository.save(servicio);
    }
}
