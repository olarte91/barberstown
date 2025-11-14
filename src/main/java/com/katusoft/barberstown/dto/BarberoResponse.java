package com.katusoft.barberstown.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class BarberoResponse {

  private UUID id;
  private String name;
  private String lastname;
  private String phone;
  private String image;
}
