package com.katusoft.barberstown.dto;

import com.katusoft.barberstown.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

  private String email;
  private String password;
  private String username;
  private Role role;
}
