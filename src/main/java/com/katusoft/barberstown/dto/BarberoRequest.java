package com.katusoft.barberstown.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BarberoRequest {

  private String name;
  private String lastname;
  private String email;
  private String phone;
  private String password;
  private String image;
}
