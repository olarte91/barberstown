package com.katusoft.barberstown.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.katusoft.barberstown.exception.BarberoNoEncontradoException;
import com.katusoft.barberstown.model.Barbero;
import com.katusoft.barberstown.repository.BarberoRepository;

@Service
public class BarberoService {

    private final BarberoRepository barberoRepository;

    public BarberoService(BarberoRepository barberoRepository){
        this.barberoRepository = barberoRepository;
    }

    public List<Barbero> getAllBarberos(){
        return barberoRepository.findAll();
    }

    public Barbero saveBarbero(Barbero barbero){
        return barberoRepository.save(barbero);
    }

    public Optional<Barbero> getBarberoById(Long id){
        return  barberoRepository.findById(id);
    }

    public boolean deleteBarberoById(Long id){
        if(!barberoRepository.existsById(id)){
            return false;
        }
        barberoRepository.deleteById(id);
        return true;
    }

    public Barbero actualizarBarbero(Long id, Barbero datosActualizados){
        Barbero barbero = barberoRepository.findById(id)
            .orElseThrow(() -> new BarberoNoEncontradoException(id));

        if(datosActualizados.getNombre() != null) barbero.setNombre(datosActualizados.getNombre());
        if(datosActualizados.getApellido() != null) barbero.setApellido(datosActualizados.getApellido());
        if(datosActualizados.getCorreo() != null) barbero.setCorreo(datosActualizados.getCorreo());
        if(datosActualizados.getTelefono() != null) barbero.setTelefono(datosActualizados.getTelefono());
        if(datosActualizados.getHoraInicio() != null) barbero.setHoraInicio(datosActualizados.getHoraInicio());
        if(datosActualizados.getHoraFin() != null) barbero.setHoraFin(datosActualizados.getHoraFin());

        return barberoRepository.save(barbero);
    }

}
