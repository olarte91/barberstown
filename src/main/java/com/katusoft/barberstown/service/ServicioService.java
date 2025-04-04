package com.katusoft.barberstown.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.katusoft.barberstown.exception.ServicioNoEncontradoException;
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

    public Servicio actualizarServicio (Long id, Servicio servicioActualizado){
        Servicio servicio = servicioRepository.findById(id)
        .orElseThrow(() -> new ServicioNoEncontradoException(id));

        if(servicioActualizado.getNombre() != null) servicio.setNombre(servicioActualizado.getNombre());
        if(servicioActualizado.getDuracion() != null) servicio.setDuracion(servicioActualizado.getDuracion());
        if(servicioActualizado.getValor() != servicio.getValor()) servicio.setValor(servicioActualizado.getValor());

        return servicioRepository.save(servicio);

    }
}
