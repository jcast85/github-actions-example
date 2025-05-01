package com.jc.crud.example.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "companies")
@Access(AccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Builder
public class Company implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "company_table_gen")
  @TableGenerator(name = "company_table_gen", table = "id_generator",
    pkColumnName = "gen_name", valueColumnName = "gen_value",
    pkColumnValue = "company_id", initialValue = 1, allocationSize = 1)
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
