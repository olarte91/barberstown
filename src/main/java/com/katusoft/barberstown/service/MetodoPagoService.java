package com.katusoft.barberstown.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.katusoft.barberstown.exception.MetodoPagoNoEncontradoException;
import com.katusoft.barberstown.model.MetodoPago;
import com.katusoft.barberstown.repository.MetodoPagoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MetodoPagoService {

    private final MetodoPagoRepository metodoPagoRepository;

    //Obtiene todos los métodos de pago
    public List<MetodoPago> getAll() {
        return metodoPagoRepository.findAll();
    }

    /*Obtiene un método de pago por su ID
     * Se lanza una excepción si no existe
     */
    public MetodoPago getById(Long id) {
        return metodoPagoRepository.findById(id)
                .orElseThrow(() -> new MetodoPagoNoEncontradoException(id));
    }

    //Guarda un nuevo método de pago
    public MetodoPago save(MetodoPago metodoPago) {
        return metodoPagoRepository.save(metodoPago);
    }

    /*Elimina un método de pago por su ID
     * Se lanza una excepción si no existe
     */
    @Transactional
    public void deleteById(Long id) {
        try {
            metodoPagoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new MetodoPagoNoEncontradoException(id);
        }
    }

    //Actualiza un método de pago existente
    public MetodoPago update(Long id, MetodoPago metodoPago) {
        MetodoPago metodoPagoExistente = metodoPagoRepository.findById(id)
                .orElseThrow(() -> new MetodoPagoNoEncontradoException(id));

        metodoPagoExistente.setNombre(metodoPago.getNombre());

        return metodoPagoRepository.save(metodoPagoExistente);

    }
}
