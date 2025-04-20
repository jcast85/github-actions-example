package com.jc.crud.example.service;

import com.jc.crud.example.model.CompanyDTO;

import java.util.List;
import java.util.Optional;

public interface CompanyCrudService {
    Integer insert(CompanyDTO companyDTO);

    Optional<CompanyDTO> update(Integer id, CompanyDTO companyDTO);

    List<CompanyDTO> findAll();

    Optional<CompanyDTO> findById(Integer id);

    boolean deleteById(Integer id);
}