package com.katusoft.barberstown.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(HttpServletRequest request,
                     HttpServletResponse response,
                     AccessDeniedException accessDeniedException) throws IOException, ServletException
  {
   response.setStatus(HttpServletResponse.SC_FORBIDDEN);
   response.setContentType("application/json");

    Map<String, Object> body = new HashMap<>();
    body.put("status", 403);
    body.put("error", "FORBIDDEN");
    body.put("message", "Se requiere rol ADMIN para acceder a este recurso");
    body.put("path", request.getRequestURI());

    new ObjectMapper().writeValue(response.getOutputStream(), body);
  }
}
