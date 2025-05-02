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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {

    private final CitaService citaService;

    @PostMapping
    public ResponseEntity<Cita> crearCita(@RequestBody CitaRequest citaRequest){
        Cita nuevaCita = citaService.crearCita(citaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCita);
    }

    @GetMapping
    public ResponseEntity<List<Cita>> obtenerCitas(){
        List<Cita> citas = citaService.obtenerCitas();
        return ResponseEntity.ok(citas);
    }

}
