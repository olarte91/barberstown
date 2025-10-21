package com.katusoft.barberstown.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Authresponse {

  private String token;
  private String message;

  public AuthResponse(String token){
    this.token = token;
  }

  public Authresponse(String token, String message){
    this.token = token;
    this.message = message;
  }
}
