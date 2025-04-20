package com.jc.crud.example.mapper;

import com.jc.crud.example.model.CompanyDTO;
import com.jc.crud.example.persistence.entity.Company;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DtoToCompanyMapper implements Function<CompanyDTO, Company> {

  @Override
  public Company apply(CompanyDTO companyDTO) {
    if (companyDTO == null) {
      return null;
    }

    return Company.builder()
      .id(companyDTO.getId())
      .name(companyDTO.getName())
      .address(companyDTO.getAddress())
      .email(companyDTO.getEmail())
      .phone(companyDTO.getPhone())
      .build();
  }
}
