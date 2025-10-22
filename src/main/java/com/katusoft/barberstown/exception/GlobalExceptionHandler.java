package com.katusoft.barberstown.exception;

import java.util.HashMap;
import java.util.Map;

import com.katusoft.barberstown.dto.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BarberoNoEncontradoException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Map<String, String> hadleBarberoNoencontradoException(BarberoNoEncontradoException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", ex.getMessage());
    return error;
  }

  @ExceptionHandler(ClienteNoEncontradoException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Map<String, String> handleClienteNoEncontradoException(ClienteNoEncontradoException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", ex.getMessage());
    return error;
  }

  @ExceptionHandler(ServicioNoEncontradoException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Map<String, String> handleServicioNoEncontradoException(ServicioNoEncontradoException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", ex.getMessage());
    return error;
  }

  @ExceptionHandler(MetodoPagoNoEncontradoException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Map<String, String> handleMetodoPagoNoEncontradoException
      (MetodoPagoNoEncontradoException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", ex.getMessage());
    return error;
  }

  @ExceptionHandler(PagoNoEncontradoException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Map<String, String> handlePagoNoEncontradoException(Exception ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", ex.getMessage());
    return error;
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<AuthResponse> handleUserAlreadyExists(UserAlreadyExistsException ex) {
    return ResponseEntity.badRequest()
        .body(new AuthResponse(null, ex.getMessage()));
  }

  @ExceptionHandler(UserEmailNotFoundException.class)
  public ResponseEntity<AuthResponse> handleUserNotFound(UserEmailNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(new AuthResponse(null, ex.getMessage()));
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<AuthResponse> handleBadCredentials(BadCredentialsException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(new AuthResponse(null, "Credenciales inválidas"));
  }
}
