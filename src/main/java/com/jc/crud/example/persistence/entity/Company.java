package com.jc.crud.example.persistence.entity;

import lombok.*;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "companies", schema = "railway")
@Access(AccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Builder
public class Company implements Serializable {

  @Id
  @Column(name = "ID", nullable = false)
  private Integer id;

  @Column(name = "NAME", length = 50)
  private String name;

  @Column(name = "ADDRESS", columnDefinition = "TEXT")
  private String address;

  @Column(name = "EMAIL", length = 50)
  private String email;

  @Column(name = "PHONE", length = 10)
  private String phone;
}
