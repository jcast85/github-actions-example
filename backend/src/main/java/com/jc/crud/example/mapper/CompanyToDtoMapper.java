package com.jc.crud.example.mapper;

import com.jc.crud.example.model.CompanyDTO;
import com.jc.crud.example.persistence.entity.Company;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CompanyToDtoMapper implements Function<Company, CompanyDTO> {

  @Override
  public CompanyDTO apply(Company entity) {
    if (entity == null) {
      return null;
    }

    return CompanyDTO.builder()
      .id(entity.getId())
      .name(entity.getName())
      .address(entity.getAddress())
      .email(entity.getEmail())
      .phone(entity.getPhone())
      .build();
  }
}
