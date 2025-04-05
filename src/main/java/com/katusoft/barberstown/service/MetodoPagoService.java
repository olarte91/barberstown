package com.katusoft.barberstown.service;

import java.util.List;

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

    public MetodoPago saveMetodoPago(MetodoPago metodoPago){
        return metodoPagoRepository.save(metodoPago);
    }
}
