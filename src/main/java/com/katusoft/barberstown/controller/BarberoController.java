package com.katusoft.barberstown.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.katusoft.barberstown.model.Barbero;
import com.katusoft.barberstown.repository.BarberoRepository;
import com.katusoft.barberstown.service.BarberoService;


@RestController
@RequestMapping("api/barberos")
public class BarberoController {


    private final BarberoService barberoService;

    public BarberoController(BarberoService barberoService, BarberoRepository barberoRepository){
        this.barberoService = barberoService;
    }

    @GetMapping
    public ResponseEntity<List<Barbero>> getAllBarberos(){
        if(barberoService.getAllBarberos() != null){
            return ResponseEntity.ok(barberoService.getAllBarberos());
        }
        return ResponseEntity.badRequest().body(Collections.emptyList());
    }

    @PostMapping
    public Barbero createBarbero(@RequestBody Barbero barbero){
        return barberoService.saveBarbero(barbero);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Barbero> getBarberoById(@PathVariable Long id) {
        Optional<Barbero> barberoOptional = barberoService.getBarberoById(id);

        return barberoOptional
        .map(barbero -> ResponseEntity.ok(barbero))
        .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBarberoById(@PathVariable Long id){
        if(!barberoService.deleteBarberoById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Barbero no encontrado");
        }
        return ResponseEntity.ok("Barbero eliminado correctamente");
    }

    @PutMapping("/{id}")
    public Barbero actualizarBarbero(@PathVariable Long id, @RequestBody Barbero barbero){
        return barberoService.actualizarBarbero(id, barbero);
    }
    
    
}
