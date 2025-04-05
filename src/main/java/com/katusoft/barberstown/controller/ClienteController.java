package com.katusoft.barberstown.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.katusoft.barberstown.model.Cliente;
import com.katusoft.barberstown.service.ClienteService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> getClientes(){
        return clienteService.getAllClientes();
    }

    @PostMapping
    public Cliente saveCliente(@RequestBody Cliente cliente){
        return clienteService.saveCliente(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id){
       Optional<Cliente> clienteOptional = clienteService.getClienteById(id);

       return clienteOptional
       .map(cliente -> ResponseEntity.ok(cliente))
       .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClienteById(@PathVariable Long id){
        if(!clienteService.deleteClienteById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }
        return ResponseEntity.ok("Cliente eliminado con éxito");
    }

    @PutMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente ){
        return clienteService.actualizarCliente(id, cliente);
    }
    

    
}
