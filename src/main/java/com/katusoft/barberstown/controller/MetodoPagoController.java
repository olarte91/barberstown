package com.katusoft.barberstown.controller;

import java.util.List;

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

import com.katusoft.barberstown.model.MetodoPago;
import com.katusoft.barberstown.service.MetodoPagoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/metodopago")
@RequiredArgsConstructor
public class MetodoPagoController {

    private final MetodoPagoService metodoPagoService;

    //Obtiene todos los métodos de pago
    @GetMapping()
    public ResponseEntity<List<MetodoPago>> getAll(){
        List<MetodoPago> metodosPago = metodoPagoService.getAll();
        return ResponseEntity.ok(metodosPago);
    }

    //Obtiene un método de pago por su ID
    @GetMapping("/{id}")
    public ResponseEntity<MetodoPago> getById(@PathVariable Long id){
        MetodoPago metodoPago = metodoPagoService.getById(id);
        return ResponseEntity.ok(metodoPago);
    }

    //Crea un nuevo método de pago
    @PostMapping()
    public ResponseEntity<MetodoPago> create(@RequestBody MetodoPago metodoPago){
        MetodoPago nuevoMetodoPago = metodoPagoService.save(metodoPago);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMetodoPago);
    }

    //Elimina un método de pago por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        metodoPagoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //Actualiza un método de pago existente
    @PutMapping("/{id}")
    public ResponseEntity<MetodoPago> updateById(@PathVariable Long id, @RequestBody MetodoPago metodoPago){
        MetodoPago metodoPagoActualizado = metodoPagoService.update(id, metodoPago);
        return ResponseEntity.ok(metodoPagoActualizado);
    }

}
