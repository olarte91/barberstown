package com.katusoft.barberstown.service;

import java.util.List;
import java.util.Optional;
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
    if(barberoRepository.existsByEmail(barberoRequest.getEmail())){
      throw new BarberoNoEncontradoException("El barbero con el email: " +  barberoRequest.getEmail() + " ya existe!");
    }
    Barbero barbero =  barberoRepository.save(this.convertToEntity(barberoRequest));

    return convertToDto(barbero);
  }

  public BarberoResponse getById(UUID id) {
    Barbero barbero = barberoRepository.findById(id)
        .orElseThrow(() -> new BarberoNoEncontradoException("El barbero con el id " + id + " no existe!"));

    BarberoResponse Dto = convertToDto(barbero);

    return Dto;

  }

  public BarberoResponse deleteById(UUID id) {
    Barbero barbero = barberoRepository.findById(id)
        .orElseThrow(() -> new BarberoNoEncontradoException("Barbero no encontrado con id: " + id));

    barberoRepository.delete(barbero);
    return convertToDto(barbero);
  }

  public Barbero update(UUID id, Barbero datosActualizados) {
    Barbero barbero = barberoRepository.findById(id)
        .orElseThrow(() -> new BarberoNoEncontradoException("Barbero no encontrado"));

    actualizarCampoSiNoNulo(datosActualizados.getName(), barbero::setName);
    actualizarCampoSiNoNulo(datosActualizados.getLastname(), barbero::setLastname);
    actualizarCampoSiNoNulo(datosActualizados.getEmail(), barbero::setEmail);
    actualizarCampoSiNoNulo(datosActualizados.getPhone(), barbero::setPhone);
    actualizarCampoSiNoNulo(datosActualizados.getImage(), barbero::setImage);

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
        barbero.getName(),
        barbero.getLastname(),
        barbero.getEmail(),
        barbero.getPhone(),
        barbero.getImage()
    );
  }

  private Barbero convertToEntity(BarberoRequest barberoRequest) {
    return new Barbero(
        barberoRequest.getName(),
        barberoRequest.getLastname(),
        barberoRequest.getPhone(),
        barberoRequest.getEmail(),
        barberoRequest.getImage()
    );
  }

}
