package com.katusoft.barberstown.controller;

import java.util.List;

import com.katusoft.barberstown.dto.AppointmentResponse;
import com.katusoft.barberstown.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.katusoft.barberstown.dto.AppointmentRequest;
import com.katusoft.barberstown.model.Appointment;
import com.katusoft.barberstown.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<AppointmentResponse>> crearCita(@RequestBody AppointmentRequest appointmentRequest){
        AppointmentResponse cita = appointmentService.crearCita(appointmentRequest);
        return ResponseEntity.ok(ApiResponse.success("Cita creada correctamente", cita));
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> obtenerCitas(){
        List<Appointment> appointments = appointmentService.obtenerCitas();
        return ResponseEntity.ok(appointments);
    }

}
