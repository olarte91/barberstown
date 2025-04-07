package com.katusoft.barberstown.exception;

public class PagoNoEncontradoException extends RuntimeException{
    public PagoNoEncontradoException(Long id){
        super("No se encontró el pago con el id: " + id);
    }

}
