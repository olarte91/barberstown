package com.katusoft.barberstown.service;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import com.katusoft.barberstown.exception.ClienteNoEncontradoException;
import com.katusoft.barberstown.model.Cliente;
import com.katusoft.barberstown.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> getAll(){
        return clienteRepository.findAll();
    }

    public Cliente save(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente getById (Long id){
        return clienteRepository.findById(id)
            .orElseThrow(() -> new ClienteNoEncontradoException(id));
    }

    public void deleteById (Long id){
        if(!clienteRepository.existsById(id)){
            throw new ClienteNoEncontradoException(id);
        }
        clienteRepository.deleteById(id);
    }

    public Cliente update(Long id, Cliente clienteActualizado){
        Cliente cliente = getById(id);

        actualizarCampoSiNoNulo(clienteActualizado.getNombre(), cliente::setNombre);
        actualizarCampoSiNoNulo(clienteActualizado.getApellido(), cliente::setApellido);
        actualizarCampoSiNoNulo(clienteActualizado.getTelefono(), cliente::setTelefono);
        actualizarCampoSiNoNulo(clienteActualizado.getCorreo(), cliente::setCorreo);

        return clienteRepository.save(cliente);
    }

    private <T> void actualizarCampoSiNoNulo(T valor, Consumer<T> setter){
        if(valor != null){
            setter.accept(valor);
        }
    }
}
