package com.katusoft.barberstown.service;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import com.katusoft.barberstown.exception.BarberoNoEncontradoException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.katusoft.barberstown.model.Barbero;
import com.katusoft.barberstown.repository.BarberoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BarberoService {

    private final BarberoRepository barberoRepository;


    public List<Barbero> getAll(){
        return barberoRepository.findAll();
    }

    public Barbero save(Barbero barbero){
        return barberoRepository.save(barbero);
    }

    public Barbero getById(UUID id){
        return  barberoRepository.findById(id)
            .orElseThrow(() -> new BarberoNoEncontradoException("Barbero no econtrado"));
    }

    public void deleteById(UUID id){
        try {
            barberoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            
        }

    }

    public Barbero update(UUID id, Barbero datosActualizados){
        Barbero barbero = barberoRepository.findById(id)
            .orElseThrow(() -> new BarberoNoEncontradoException("Barbero no encontrado"));

        actualizarCampoSiNoNulo(datosActualizados.getNombre(), barbero::setNombre);
        actualizarCampoSiNoNulo(datosActualizados.getApellido(), barbero::setApellido);
        actualizarCampoSiNoNulo(datosActualizados.getCorreo(), barbero::setCorreo);
        actualizarCampoSiNoNulo(datosActualizados.getTelefono(), barbero::setTelefono);
        actualizarCampoSiNoNulo(datosActualizados.getImagen(), barbero::setImagen);

        return barberoRepository.save(barbero);
    }
    
    private <T> void actualizarCampoSiNoNulo(T valor, Consumer<T> setter){
        if(valor != null){
            setter.accept(valor);
        }
    }
    
}
