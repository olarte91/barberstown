package com.katusoft.barberstown.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.katusoft.barberstown.model.Servicio;
import com.katusoft.barberstown.service.ServicioService;

@RestController
@RequestMapping("api/servicios")
public class ServicioController {
    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService){
        this.servicioService = servicioService;
    }

    @GetMapping
    public List<Servicio> getAllServicios(){
        return servicioService.getAllServicios();
    }

    @PostMapping
    public Servicio saveServicio(@RequestBody Servicio servicio){
        
        return servicioService.saveServicio(servicio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getServicioById(@PathVariable Long id){
        Optional<Servicio> servicioOptional = servicioService.getServicioById(id);

        return servicioOptional
        .map(servicio -> ResponseEntity.ok(servicio))
        .orElseGet(() -> ResponseEntity.notFound().build());

    }
}
