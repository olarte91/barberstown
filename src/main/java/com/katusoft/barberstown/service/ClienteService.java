package com.katusoft.barberstown.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
}
