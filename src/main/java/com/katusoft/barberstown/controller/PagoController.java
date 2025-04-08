package com.katusoft.barberstown.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.katusoft.barberstown.exception.PagoNoEncontradoException;
import com.katusoft.barberstown.model.Pago;
import com.katusoft.barberstown.service.PagoService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService pagoService;
    
    public PagoController(PagoService pagoService){
        this.pagoService = pagoService;
    }

    @GetMapping()
    public List<Pago> getAllPagos(){
        return pagoService.getAllPagos();
    }
    
    @GetMapping("/{id}")
    public Pago getPagoById(@PathVariable Long id){
        return pagoService.getPagoById(id)
        .orElseThrow(() -> new PagoNoEncontradoException(id));
    }

    @PostMapping
    public Pago savePago(@RequestBody Pago pago){
        return pagoService.savePago(pago);
    }

    @DeleteMapping("/{id}")
    public String deletePago(@PathVariable Long id){
        if(pagoService.deletePago(id)){
            return "Pago eliminado correctamente";
        }
        return "No se encontró el pago con el id: " + id;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> updatePago(@PathVariable Long id, @RequestBody Pago pago){
        Pago pagoActualizado = pagoService.updatePago(id, pago);
        if(pagoActualizado != null){
            return ResponseEntity.ok(pagoActualizado);
        }
        return ResponseEntity.notFound().build();
    }

}
