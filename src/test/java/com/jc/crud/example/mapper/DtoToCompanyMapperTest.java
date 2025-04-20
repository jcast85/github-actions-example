package com.jc.crud.example.mapper;

import com.jc.crud.example.model.CompanyDTO;
import com.jc.crud.example.persistence.entity.Company;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DtoToCompanyMapperTest {

  private final DtoToCompanyMapper mapper = new DtoToCompanyMapper();

  @Test
  void testApply() {
    // Arrange
    CompanyDTO companyDTO = CompanyDTO.builder()
      .id(1)
      .name("Tech Corp")
      .address("123 Tech Street")
      .email("contact@techcorp.com")
      .phone("1234567890")
      .build();

    // Act
    Company company = mapper.apply(companyDTO);

    // Assert
    assertNotNull(company);
    assertEquals(1, company.getId());
    assertEquals("Tech Corp", company.getName());
    assertEquals("123 Tech Street", company.getAddress());
    assertEquals("contact@techcorp.com", company.getEmail());
    assertEquals("1234567890", company.getPhone());
  }

  @Test
  void testApplyNull() {
    // Act
    Company company = mapper.apply(null);

    // Assert
    assertNull(company);
  }
}
