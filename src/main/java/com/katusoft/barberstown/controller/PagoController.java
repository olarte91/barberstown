package com.katusoft.barberstown.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.katusoft.barberstown.model.Pago;
import com.katusoft.barberstown.service.PagoService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

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
    
    

}
