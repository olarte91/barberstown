package com.katusoft.barberstown.exception;

public class MetodoPagoNoEncontradoException extends RuntimeException{
    public MetodoPagoNoEncontradoException(Long id){
        super("Método de pago no encontrado con id: " + id);
    }

}
