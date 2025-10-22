package com.katusoft.barberstown.service;

import com.katusoft.barberstown.dto.AuthResponse;
import com.katusoft.barberstown.dto.LoginRequest;
import com.katusoft.barberstown.dto.RegisterRequest;
import com.katusoft.barberstown.exception.UserAlreadyExistsException;
import com.katusoft.barberstown.exception.UserEmailNotFoundException;
import com.katusoft.barberstown.jwt.JwtService;
import com.katusoft.barberstown.model.User;
import com.katusoft.barberstown.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public AuthResponse register(RegisterRequest request) {
    if(userRepository.existsByEmail(request.getEmail())) {
      throw new UserAlreadyExistsException("El email " + request.getEmail() + " ya está registrado");
    }

    User user = User.builder()
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .username(request.getUsername())
        .role(request.getRole())
        .build();

    userRepository.save(user);

    String token = jwtService.generateToken(user);

    return new AuthResponse(token, "Usuario registrado exitosamente");
  }

  public AuthResponse login(LoginRequest request) {
    User user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new UserEmailNotFoundException("Usuario no registrado"));

    if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new BadCredentialsException("Contraseña incorrecta");
    }

    String token = jwtService.generateToken(user);

    return new AuthResponse(token, "Login exitoso");
  }
}
