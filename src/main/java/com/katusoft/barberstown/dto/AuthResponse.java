package com.katusoft.barberstown.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

  private String token;
  private String message;

  public AuthResponse(String token){
    this.token = token;
  }

  public AuthResponse(String token, String message){
    this.token = token;
    this.message = message;
  }
}
