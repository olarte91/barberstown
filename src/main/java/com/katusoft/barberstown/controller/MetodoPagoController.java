package com.katusoft.barberstown.controller;

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

import com.katusoft.barberstown.model.MetodoPago;
import com.katusoft.barberstown.service.MetodoPagoService;

@RestController
@RequestMapping("/api/metodopago")
public class MetodoPagoController {

    private final MetodoPagoService metodoPagoService;

    public MetodoPagoController(MetodoPagoService metodoPagoService) {
        this.metodoPagoService = metodoPagoService;
    }

    @GetMapping()
    public List<MetodoPago> getAllMetodosPago() {
        return metodoPagoService.getAllMetodosPago();
    }

    @GetMapping("/{id}")
    public Optional<MetodoPago> getMetodoPagoById(@PathVariable Long id) {
        return metodoPagoService.getMetodoPagoById(id);
    }

    @PostMapping()
    public MetodoPago saveMetodoPago(@RequestBody MetodoPago metodoPago) {
        return metodoPagoService.saveMetodoPago(metodoPago);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMetodoPagoById(@PathVariable Long id) {
        if (!metodoPagoService.deleteMetodoDePagoById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Método de pago no encontrado");

        }
        return ResponseEntity.ok("Método de pago eliminado correctamente");
    }

    @PutMapping("/{id}")
    public MetodoPago actualizarMetodoPago(@PathVariable Long id, @RequestBody MetodoPago metodoPago){
        return metodoPagoService.actualizarMetodoPago(id, metodoPago);
    }

}
