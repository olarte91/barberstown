package com.katusoft.barberstown.service;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import com.katusoft.barberstown.dto.BarberoRequest;
import com.katusoft.barberstown.dto.BarberoResponse;
import com.katusoft.barberstown.exception.BarberoNoEncontradoException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.katusoft.barberstown.model.Barbero;
import com.katusoft.barberstown.repository.BarberoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BarberoService {

  private final BarberoRepository barberoRepository;


  public List<BarberoResponse> getAll() {
    return barberoRepository.findAll().stream()
        .map(this::convertToDto)
        .toList();
  }

  public BarberoResponse save(BarberoRequest barberoRequest) {
    if(barberoRequest == null){
      throw new BarberoNoEncontradoException("No ha proporcionado datos del barbero");
    }
    Barbero barbero =  barberoRepository.save(this.convertToEntity(barberoRequest));

    return convertToDto(barbero);
  }

  public Barbero getById(UUID id) {
    return barberoRepository.findById(id)
        .orElseThrow(() -> new BarberoNoEncontradoException("Barbero no econtrado"));
  }

  public void deleteById(UUID id) {
    try {
      barberoRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {

    }

  }

  public Barbero update(UUID id, Barbero datosActualizados) {
    Barbero barbero = barberoRepository.findById(id)
        .orElseThrow(() -> new BarberoNoEncontradoException("Barbero no encontrado"));

    actualizarCampoSiNoNulo(datosActualizados.getNombre(), barbero::setNombre);
    actualizarCampoSiNoNulo(datosActualizados.getApellido(), barbero::setApellido);
    actualizarCampoSiNoNulo(datosActualizados.getCorreo(), barbero::setCorreo);
    actualizarCampoSiNoNulo(datosActualizados.getTelefono(), barbero::setTelefono);
    actualizarCampoSiNoNulo(datosActualizados.getImagen(), barbero::setImagen);

    return barberoRepository.save(barbero);
  }

  private <T> void actualizarCampoSiNoNulo(T valor, Consumer<T> setter) {
    if (valor != null) {
      setter.accept(valor);
    }
  }

  private BarberoResponse convertToDto(Barbero barbero) {
    return new BarberoResponse(
        barbero.getId(),
        barbero.getNombre(),
        barbero.getApellido(),
        barbero.getCorreo(),
        barbero.getTelefono(),
        barbero.getImagen()
    );
  }

  private Barbero convertToEntity(BarberoRequest barberoRequest) {
    return new Barbero(
        barberoRequest.getName(),
        barberoRequest.getLastname(),
        barberoRequest.getEmail(),
        barberoRequest.getPhone(),
        barberoRequest.getImage()
    );
  }

}
