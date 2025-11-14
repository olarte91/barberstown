package com.katusoft.barberstown.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.katusoft.barberstown.model.Service;
import com.katusoft.barberstown.repository.ServicioRepository;

@org.springframework.stereotype.Service
public class ServicioService {
    private final ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository){
        this.servicioRepository = servicioRepository;
    }

    public List<Service> getAllServicios(){
        return servicioRepository.findAll();
    }

    public Service saveServicio(Service service){
        
        return servicioRepository.save(service);
    }

    public Optional<Service> getServicioById(UUID id){
        return servicioRepository.findById(id);
    }

    public boolean deleteServicioById(UUID id){
        if(!servicioRepository.existsById(id)){
            return false;
        }
        servicioRepository.deleteById(id);
        return true;
    }

//    public Servicio actualizarServicio (UUID id, Servicio servicioActualizado){
//        Servicio servicio = servicioRepository.findById(id)
//        .orElseThrow(() -> new ServicioNoEncontradoException("Servicio no econtrado"));
//
//        if(servicioActualizado.getNombre() != null) servicio.setNombre(servicioActualizado.getNombre());
//        if(servicioActualizado.getDuracion() != null) servicio.setDuracion(servicioActualizado.getDuracion());
//        if(servicioActualizado.getValor() != servicio.getValor()) servicio.setValor(servicioActualizado.getValor());
//
//        return servicioRepository.save(servicio);
//
//    }
}
