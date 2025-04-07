package com.katusoft.barberstown.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.katusoft.barberstown.model.Pago;
import com.katusoft.barberstown.repository.PagoRepository;


@Service
public class PagoService {

    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository){
        this.pagoRepository = pagoRepository;
    }

    public List<Pago> getAllPagos(){
        return pagoRepository.findAll();
    }

    public Optional<Pago> getPagoById(Long id){
        return pagoRepository.findById(id);
    }

    public Pago savePago(Pago pago){
        return pagoRepository.save(pago);
    }


}
