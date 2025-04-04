package com.katusoft.barberstown.exception;

public class BarberoNoEncontradoException extends RuntimeException{

    public BarberoNoEncontradoException(Long id){
        super("Barbero no encontrado con id: " + id);
    }
}
