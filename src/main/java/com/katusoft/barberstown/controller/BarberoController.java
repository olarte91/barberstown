package com.katusoft.barberstown.controller;

import java.util.List;
import java.util.UUID;

import com.katusoft.barberstown.dto.BarberoRequest;
import com.katusoft.barberstown.dto.BarberoResponse;
import com.katusoft.barberstown.exception.BarberoNoEncontradoException;
import com.katusoft.barberstown.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.katusoft.barberstown.model.Barbero;
import com.katusoft.barberstown.service.BarberoService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/barberos")
public class BarberoController {


  private final BarberoService barberoService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping
  public ResponseEntity<ApiResponse<List<BarberoResponse>>> getAll() {
    List<BarberoResponse> allBarbers = barberoService.getAll();
    ApiResponse<List<BarberoResponse>> response =
        ApiResponse.success("Barberos obtenidos exitosamente", allBarbers);
    return ResponseEntity.ok(response);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ResponseEntity<ApiResponse<BarberoResponse>> create(@RequestBody BarberoRequest request) {
    BarberoResponse nuevoBarbero = barberoService.save(request);
    return ResponseEntity.ok(ApiResponse.success("Barbero creado con éxito", nuevoBarbero));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<BarberoResponse>> getById(@PathVariable UUID id) {
    BarberoResponse barbero = barberoService.getById(id);
    return ResponseEntity.ok(ApiResponse.success("Barbero encontrado con éxito", barbero));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<BarberoResponse>> deleteBarberById(@PathVariable UUID id) {
    BarberoResponse barbero = barberoService.deleteById(id);
    return ResponseEntity.ok(ApiResponse.success("Barbero eliminado con éxito", barbero));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Barbero> update(@PathVariable UUID id, @RequestBody Barbero barbero) {
    try {
      Barbero barberoActualizado = barberoService.update(id, barbero);
      return ResponseEntity.ok(barberoActualizado);
    } catch (BarberoNoEncontradoException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }


}
