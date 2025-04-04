package com.katusoft.barberstown.exception;

public class ServicioNoEncontradoException extends RuntimeException{
    public ServicioNoEncontradoException(Long id){
        super("Servicio no encontrado con id: " + id);
    }
}
