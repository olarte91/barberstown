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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteServicioById(@PathVariable Long id){
        if(!servicioService.deleteServicioById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok("Servicio eliminado correctamente");
    }

    @PutMapping("/{id}")
    public Servicio actualizarServicio(@PathVariable Long id, @RequestBody Servicio servicio){
        return servicioService.actualizarServicio(id, servicio);
    }
}
