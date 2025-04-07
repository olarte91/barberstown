package com.katusoft.barberstown.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.katusoft.barberstown.model.MetodoPago;
import com.katusoft.barberstown.repository.MetodoPagoRepository;

@Service
public class MetodoPagoService {

    private final MetodoPagoRepository metodoPagoRepository;

    public MetodoPagoService(MetodoPagoRepository metodoPagoRepository){
        this.metodoPagoRepository = metodoPagoRepository;
    }

    public List<MetodoPago> getAllMetodosPago (){
        return metodoPagoRepository.findAll();
    }

    public Optional<MetodoPago> getMetodoPagoById(Long id){
        return metodoPagoRepository.findById(id);
    }

    public MetodoPago saveMetodoPago(MetodoPago metodoPago){
        return metodoPagoRepository.save(metodoPago);
    }

    public boolean deleteMetodoDePagoById(Long id){
        if(!metodoPagoRepository.existsById(id)){
            return false;
        }
        metodoPagoRepository.deleteById(id);
        return true;
    }
}
