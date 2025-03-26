package com.katusoft.barberstown.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

}
