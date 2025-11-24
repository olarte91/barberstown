package com.katusoft.barberstown.service;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import com.katusoft.barberstown.dto.BarberoRequest;
import com.katusoft.barberstown.dto.BarberoResponse;
import com.katusoft.barberstown.dto.RegisterRequest;
import com.katusoft.barberstown.exception.BarberoNoEncontradoException;
import com.katusoft.barberstown.model.Barber;
import com.katusoft.barberstown.model.User;
import org.springframework.stereotype.Service;

import com.katusoft.barberstown.repository.BarberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BarberService {

  private final BarberRepository barberRepository;

  public Barber createBarberProfile(User user, RegisterRequest request){
    Barber barber = Barber.builder()
        .user(user)
        .name(request.getName())
        .lastname(request.getLastname())
        .phone(request.getPhone())
        .build();

    return barberRepository.save(barber);
  }

  public List<BarberoResponse> getAll() {
    return barberRepository.findAll().stream()
        .map(this::convertToDto)
        .toList();
  }


  public BarberoResponse getById(UUID id) {
    Barber barber = barberRepository.findById(id)
        .orElseThrow(() -> new BarberoNoEncontradoException("El barbero con el id " + id + " no existe!"));

    BarberoResponse Dto = convertToDto(barber);

    return Dto;

  }

  public BarberoResponse deleteById(UUID id) {
    Barber barber = barberRepository.findById(id)
        .orElseThrow(() -> new BarberoNoEncontradoException("Barbero no encontrado con id: " + id));

    barberRepository.delete(barber);
    return convertToDto(barber);
  }

  public Barber update(UUID id, Barber datosActualizados) {
    Barber barber = barberRepository.findById(id)
        .orElseThrow(() -> new BarberoNoEncontradoException("Barbero no encontrado"));

    actualizarCampoSiNoNulo(datosActualizados.getName(), barber::setName);
    actualizarCampoSiNoNulo(datosActualizados.getLastname(), barber::setLastname);
    actualizarCampoSiNoNulo(datosActualizados.getPhone(), barber::setPhone);
    actualizarCampoSiNoNulo(datosActualizados.getImage(), barber::setImage);

    return barberRepository.save(barber);
  }

  private <T> void actualizarCampoSiNoNulo(T valor, Consumer<T> setter) {
    if (valor != null) {
      setter.accept(valor);
    }
  }

  private BarberoResponse convertToDto(Barber barber) {
    return new BarberoResponse(
        barber.getId(),
        barber.getName(),
        barber.getLastname(),
        barber.getPhone(),
        barber.getImage()
    );
  }

  private Barber convertToEntity(BarberoRequest barberoRequest) {
    return Barber.builder()
        .name(barberoRequest.getName())
        .lastname(barberoRequest.getLastname())
        .phone(barberoRequest.getPhone())
        .image(barberoRequest.getImage()).build();
  }

}
