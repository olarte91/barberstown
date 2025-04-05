package com.katusoft.barberstown.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.katusoft.barberstown.model.MetodoPago;
import com.katusoft.barberstown.service.MetodoPagoService;

@RestController
@RequestMapping("/api/metodopago")
public class MetodoPagoController {

    private final MetodoPagoService metodoPagoService;

    public MetodoPagoController(MetodoPagoService metodoPagoService){
        this.metodoPagoService = metodoPagoService;
    }

    @GetMapping()
    public List<MetodoPago> getAllMetodosPago(){
        return metodoPagoService.getAllMetodosPago();
    }

    @PostMapping()
    public MetodoPago saveMetodoPago(@RequestBody MetodoPago metodoPago){
        return metodoPagoService.saveMetodoPago(metodoPago);
    }
}
