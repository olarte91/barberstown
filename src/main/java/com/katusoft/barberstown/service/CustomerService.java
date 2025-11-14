package com.katusoft.barberstown.service;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import com.katusoft.barberstown.dto.RegisterRequest;
import com.katusoft.barberstown.exception.ClienteNoEncontradoException;
import com.katusoft.barberstown.model.Customer;
import com.katusoft.barberstown.model.User;
import org.springframework.stereotype.Service;

import com.katusoft.barberstown.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

  public Customer createCustomerProfile(User user, RegisterRequest request){
    Customer customer = Customer.builder()
        .user(user)
        .name(request.getName())
        .lastName(request.getLastname())
        .phone(request.getPhone()).build();

    return customerRepository.save(customer);
  }
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    public Customer getById (UUID id){
        return customerRepository.findById(id)
            .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado"));
    }

    public void deleteById (UUID id){
        if(!customerRepository.existsById(id)){
            throw new ClienteNoEncontradoException("Cliente no encontrado");
        }
        customerRepository.deleteById(id);
    }

    public Customer update(UUID id, Customer customerActualizado){
        Customer customer = getById(id);

        actualizarCampoSiNoNulo(customerActualizado.getName(), customer::setName);
        actualizarCampoSiNoNulo(customerActualizado.getLastName(), customer::setLastName);
        actualizarCampoSiNoNulo(customerActualizado.getPhone(), customer::setPhone);

        return customerRepository.save(customer);
    }

    private <T> void actualizarCampoSiNoNulo(T valor, Consumer<T> setter){
        if(valor != null){
            setter.accept(valor);
        }
    }
}
