package com.jc.crud.example.service;

import com.jc.crud.example.mapper.CompanyToDtoMapper;
import com.jc.crud.example.mapper.DtoToCompanyMapper;
import com.jc.crud.example.model.CompanyDTO;
import com.jc.crud.example.persistence.entity.Company;
import com.jc.crud.example.persistence.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SimpleCompanyCrudService implements CompanyCrudService {
  private final CompanyRepository companyRepository;
  private final CompanyToDtoMapper companyToDtoMapper;
  private final DtoToCompanyMapper dtoToCompanyMapper;

  public SimpleCompanyCrudService(
    CompanyRepository companyRepository,
    CompanyToDtoMapper companyToDtoMapper,
    DtoToCompanyMapper dtoToCompanyMapper
  ) {
    this.companyRepository = companyRepository;
    this.companyToDtoMapper = companyToDtoMapper;
    this.dtoToCompanyMapper = dtoToCompanyMapper;
  }

  @Override
  public Integer insert(CompanyDTO companyDTO) {
    Company company = dtoToCompanyMapper.apply(companyDTO);
    company = companyRepository.save(company);
    return company.getId();
  }

  @Override
  public Optional<CompanyDTO> update(Integer id, CompanyDTO companyDTO) {
    return companyRepository.findById(id)
      .map(company -> companyRepository.save(
        dtoToCompanyMapper.apply(companyDTO.toBuilder()
          .id(id)
          .build())))
      .map(companyToDtoMapper);
  }

  @Override
  public List<CompanyDTO> findAll() {
    return companyRepository.findAll().stream()
      .map(companyToDtoMapper)
      .toList();
  }

  @Override
  public Optional<CompanyDTO> findById(Integer id) {
    return companyRepository.findById(id)
      .map(companyToDtoMapper);
  }

  @Override
  public boolean deleteById(Integer id) {
    return companyRepository.findById(id)
      .map(company -> {
        companyRepository.delete(company);
        return true;
      })
      .orElse(false);
  }
}
