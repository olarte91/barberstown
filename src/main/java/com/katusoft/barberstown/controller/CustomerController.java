package com.katusoft.barberstown.controller;

import com.katusoft.barberstown.exception.ClienteNoEncontradoException;
import com.katusoft.barberstown.model.Customer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.katusoft.barberstown.service.CustomerService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

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
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAll(){
        List<Customer> customers = customerService.getAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable UUID id){
       try{
        Customer customer = customerService.getById(id);
        return ResponseEntity.ok(customer);
       } catch(ClienteNoEncontradoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable UUID id){
        try{
            customerService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch(ClienteNoEncontradoException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable UUID id, @RequestBody Customer customer){
        try{
            Customer customerActualizado = customerService.update(id, customer);
            return ResponseEntity.ok(customerActualizado);
        } catch(ClienteNoEncontradoException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    

    
}
