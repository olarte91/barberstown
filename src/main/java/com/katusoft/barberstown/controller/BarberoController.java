package com.katusoft.barberstown.controller;

import java.util.List;
import java.util.UUID;

import com.katusoft.barberstown.exception.BarberoNoEncontradoException;
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
import com.katusoft.barberstown.service.BarberoService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/barberos")
public class BarberoController {


    private final BarberoService barberoService;

    @GetMapping
    public ResponseEntity<List<Barbero>> getAll(){
        List<Barbero> barberos = barberoService.getAll();
        return ResponseEntity.ok(barberos);
    }

    @PostMapping
    public ResponseEntity<Barbero> create(@RequestBody Barbero barbero){
        Barbero nuevoBarbero = barberoService.save(barbero);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoBarbero);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Barbero> getById(@PathVariable UUID id) {
        try {
            Barbero barbero = barberoService.getById(id);
            return ResponseEntity.ok(barbero);
        } catch (BarberoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBarberoById(@PathVariable UUID id){
        try {
            barberoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (BarberoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Barbero> update(@PathVariable UUID id, @RequestBody Barbero barbero){
        try{
            Barbero barberoActualizado = barberoService.update(id, barbero);
            return ResponseEntity.ok(barberoActualizado);
        }catch(BarberoNoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    
}
