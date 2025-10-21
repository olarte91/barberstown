package com.katusoft.barberstown.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
    this.jwtService = jwtService;
    this.userDetailsService = userDetailsService;
  }


  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    //Obtener el header Authorization
    final String authHeader = request.getHeader("Authorization");

    //Verificar si tiene el formato "Bearer token"
    if(authHeader == null || !authHeader.startsWith("Bearer ")){
      filterChain.doFilter(request, response);
      return;
    }

    //Extraer el token (quitar "Bearer )
    final String jwt = authHeader.substring(7);
    final String userEmail = jwtService.extractUsername(jwt);

    //Si hay usuario y no está autenticado aún
    if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      //Cargar el usuario de la base de datos
      UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

      //Validar el token
      if (jwtService.isTokenValid(jwt, userDetails)) {
        //Crear el token de autenticación
        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
            );

        authToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
        );

        //Establece la autenticación en el contexto de Spring Security
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }

    //Continuar con la cadena de filtros
    filterChain.doFilter(request, response);
  }
}
