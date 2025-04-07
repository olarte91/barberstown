package com.katusoft.barberstown.service;

import java.util.List;

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


}
