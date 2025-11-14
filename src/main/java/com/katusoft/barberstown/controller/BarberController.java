package com.katusoft.barberstown.controller;

import java.util.List;
import java.util.UUID;

import com.katusoft.barberstown.dto.BarberoRequest;
import com.katusoft.barberstown.dto.BarberoResponse;
import com.katusoft.barberstown.exception.BarberoNoEncontradoException;
import com.katusoft.barberstown.model.Barber;
import com.katusoft.barberstown.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.katusoft.barberstown.service.BarberService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/barberos")
public class BarberController {


  private final BarberService barberService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping
  public ResponseEntity<ApiResponse<List<BarberoResponse>>> getAll() {
    List<BarberoResponse> allBarbers = barberService.getAll();
    ApiResponse<List<BarberoResponse>> response =
        ApiResponse.success("Barberos obtenidos exitosamente", allBarbers);
    return ResponseEntity.ok(response);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<BarberoResponse>> getById(@PathVariable UUID id) {
    BarberoResponse barbero = barberService.getById(id);
    return ResponseEntity.ok(ApiResponse.success("Barbero encontrado con éxito", barbero));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<BarberoResponse>> deleteBarberById(@PathVariable UUID id) {
    BarberoResponse barbero = barberService.deleteById(id);
    return ResponseEntity.ok(ApiResponse.success("Barbero eliminado con éxito", barbero));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Barber> update(@PathVariable UUID id, @RequestBody Barber barber) {
    try {
      Barber barberActualizado = barberService.update(id, barber);
      return ResponseEntity.ok(barberActualizado);
    } catch (BarberoNoEncontradoException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }


}
