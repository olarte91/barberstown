package com.katusoft.barberstown.service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Servicio> getServicioById(Long id){
        return servicioRepository.findById(id);
    }

    public boolean deleteServicioById(Long id){
        if(!servicioRepository.existsById(id)){
            return false;
        }
        servicioRepository.deleteById(id);
        return true;
    }
}
