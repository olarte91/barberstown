package com.katusoft.barberstown.dto;

import com.katusoft.barberstown.enums.Role;
import com.katusoft.barberstown.enums.UserStatus;
import com.katusoft.barberstown.enums.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

  private String email;
  private String password;
  private String username;
  private String name;
  private String lastname;
  private String image;
  private String phone;
  private Role role;
  private UserType userType;
  private UserStatus userStatus = UserStatus.ACTIVE;
}
