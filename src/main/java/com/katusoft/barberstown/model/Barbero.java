package com.katusoft.barberstown.model;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "barberos")
@Getter
@Setter
@NoArgsConstructor
public class Barbero {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String lastname;

  @Column
  private String phone;

  @Column
  private String email;

  @Column
  private String image;

  public Barbero(String nombre, String apellido, String telefono, String correo, String imagen) {
    this.name = nombre;
    this.lastname = apellido;
    this.phone = telefono;
    this.email = correo;
    this.image = imagen;
  }

}
