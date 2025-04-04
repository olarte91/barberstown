package com.katusoft.barberstown.exception;

public class ClienteNoEncontradoException extends RuntimeException{
    public ClienteNoEncontradoException(Long id){
        super("Barbero no encontrado con id: " + id);
    }
}
