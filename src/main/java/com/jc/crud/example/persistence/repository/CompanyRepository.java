package com.jc.crud.example.persistence.repository;

import com.jc.crud.example.persistence.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
