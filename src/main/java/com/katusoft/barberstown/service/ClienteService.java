package com.katusoft.barberstown.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.katusoft.barberstown.exception.ClienteNoEncontradoException;
import com.katusoft.barberstown.model.Cliente;
import com.katusoft.barberstown.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getAllClientes(){
        return clienteRepository.findAll();
    }

    public Cliente saveCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> getClienteById(Long id){
        return clienteRepository.findById(id);
    }

    public boolean deleteClienteById(Long id){
        if(!clienteRepository.existsById(id)){
            return false;
        }
        clienteRepository.deleteById(id);
        return true;
    }

    public Cliente actualizarCliente(Long id, Cliente clienteActualizado){
        Cliente cliente = clienteRepository.findById(id)
        .orElseThrow(() -> new ClienteNoEncontradoException(id));

        if(clienteActualizado.getNombre() != null) cliente.setNombre(clienteActualizado.getNombre());
        if(clienteActualizado.getApellido() != null) cliente.setApellido(clienteActualizado.getApellido());
        if(clienteActualizado.getTelefono() != null) cliente.setTelefono(clienteActualizado.getTelefono());
        if(clienteActualizado.getCorreo() != null) cliente.setCorreo(clienteActualizado.getCorreo());

        return clienteRepository.save(cliente);
    }
}
