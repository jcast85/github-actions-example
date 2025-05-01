package com.jc.crud.example.mapper;

import com.jc.crud.example.model.CompanyDTO;
import com.jc.crud.example.persistence.entity.Company;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyToDtoMapperTest {

  private final CompanyToDtoMapper companyMapper = new CompanyToDtoMapper();

  @Test
  void testApply() {
    // Arrange
    Company entity = Company.builder()
      .id(1)
      .name("Tech Corp")
      .address("123 Tech Street")
      .email("contact@techcorp.com")
      .phone("1234567890")
      .build();

    // Act
    CompanyDTO dto = companyMapper.apply(entity);

    // Assert
    assertNotNull(dto);
    assertEquals(1, dto.getId());
    assertEquals("Tech Corp", dto.getName());
    assertEquals("123 Tech Street", dto.getAddress());
    assertEquals("contact@techcorp.com", dto.getEmail());
    assertEquals("1234567890", dto.getPhone());
  }

  @Test
  void testApplyNull() {
    // Act
    CompanyDTO dto = companyMapper.apply(null);

    // Assert
    assertNull(dto);
  }
}
