package com.jc.crud.example.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Builder(toBuilder = true)
@Schema(description = "Company DTO")
public class CompanyDTO implements Serializable {

  private Integer id;
  private String name;
  private String address;
  private String email;
  private String phone;
}
