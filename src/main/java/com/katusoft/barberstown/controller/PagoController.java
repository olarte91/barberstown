package com.katusoft.barberstown.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.katusoft.barberstown.exception.PagoNoEncontradoException;
import com.katusoft.barberstown.model.Pago;
import com.katusoft.barberstown.service.PagoService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

}
