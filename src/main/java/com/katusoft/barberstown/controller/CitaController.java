package com.katusoft.barberstown.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.katusoft.barberstown.dto.CitaRequest;
import com.katusoft.barberstown.model.Cita;
import com.katusoft.barberstown.service.CitaService;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService){
        this.citaService = citaService;
    }

    @PostMapping
    public ResponseEntity<?> crearCita(@RequestBody CitaRequest citaRequest){
        try {
            Cita nuevaCita = citaService.crearCita(citaRequest);
            return ResponseEntity.ok(nuevaCita);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error: " + e.getMessage());
        }
        
    }

    @GetMapping
    public ResponseEntity<List<Cita>> obtenerCitas(){
        List<Cita> citas = citaService.obtenerCitas();

        return ResponseEntity.ok(citas);
    }
}
