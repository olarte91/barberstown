package com.katusoft.barberstown.controller;

import com.katusoft.barberstown.dto.AuthResponse;
import com.katusoft.barberstown.dto.LoginRequest;
import com.katusoft.barberstown.dto.RegisterRequest;
import com.katusoft.barberstown.jwt.JwtService;
import com.katusoft.barberstown.model.User;
import com.katusoft.barberstown.repository.UserRepository;
import io.micrometer.core.ipc.http.HttpSender.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public AuthController(AuthenticationManager authenticationManager,
                        JwtService jwtService,
                        UserRepository userRepository,
                        PasswordEncoder passwordEncoder) {
    this.authenticationManager = authenticationManager;
    this.jwtService = jwtService;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
      );

      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      String token = jwtService.generateToken(userDetails);

      return ResponseEntity.ok(new AuthResponse(token, "Login exitoso"));
  }

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
    if(userRepository.existsByEmail(request.getEmail())) {
      return ResponseEntity.badRequest()
          .body(new AuthResponse(null, "El email ya está registrado"));
    }

    User user = new User(
        request.getEmail(),
        passwordEncoder.encode(request.getPassword()),
        request.getUsername(),
        request.getRole()
    );

    userRepository.save(user);
    String token = jwtService.generateToken(user);

    return ResponseEntity.ok(new AuthResponse(token, "Usuario registrado exitosamente"));
  }
}
