package com.katusoft.barberstown.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.katusoft.barberstown.model.Barbero;
import com.katusoft.barberstown.service.BarberoService;

@RestController
@RequestMapping("api/barberos")
public class BarberoController {

    private final BarberoService barberoService;

    public BarberoController(BarberoService barberoService){
        this.barberoService = barberoService;
    }

    @GetMapping
    public List<Barbero> getAllBarberos(){
        return barberoService.getAllBarberos();
    }

    @PostMapping
    public Barbero createBarbero(@RequestBody Barbero barbero){
        return barberoService.saveBarbero(barbero);
    }
}
