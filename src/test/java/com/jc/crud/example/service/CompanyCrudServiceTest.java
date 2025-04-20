package com.jc.crud.example.service;

import com.jc.crud.example.mapper.CompanyToDtoMapper;
import com.jc.crud.example.mapper.DtoToCompanyMapper;
import com.jc.crud.example.model.CompanyDTO;
import com.jc.crud.example.persistence.entity.Company;
import com.jc.crud.example.persistence.repository.CompanyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyCrudServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    private CompanyCrudService companyCrudService;

    @BeforeEach
    void setUp() {
        companyCrudService = new SimpleCompanyCrudService(companyRepository, new CompanyToDtoMapper(), new DtoToCompanyMapper());
    }

    @Test
    void save_shouldCallRepositoryInsert() {
        // Arrange
        String phone = "1234567890";
        String name = "Tech Corp";
        String address = "123 Tech Street";
        String email = "contact@techcorp.com";
        CompanyDTO companyDTO = CompanyDTO.builder()
          .phone(phone)
          .name(name)
          .address(address)
          .email(email)
          .build();
        Mockito.when(companyRepository.save(ArgumentMatchers.any(Company.class)))
          .thenReturn(Company.builder()
            .id(1)
            .phone(phone)
            .name(name)
            .address(address)
            .email(email)
            .build());

        // Act
        companyCrudService.insert(companyDTO);

        // Assert
        Mockito.verify(companyRepository, times(1))
          .save(ArgumentMatchers.argThat(company -> phone.equals(company.getPhone())
          && name.equals(company.getName())
          && address.equals(company.getAddress())
          && email.equals(company.getEmail())));
    }


    @Test
    void update_shouldUpdateAnExistingCompany() {
        // Arrange
        Integer id = 1;
        String oldPhone = "0123456789";
        String phone = "1234567890";
        String oldName = "Old Tech Corp";
        String name = "Tech Corp";
        String oldAddress = "123 Tech Old Street";
        String address = "123 Tech Street";
        String oldEmail = "oldcontact@techcorp.com";
        String email = "contact@techcorp.com";
        CompanyDTO companyDTO = CompanyDTO.builder()
          .phone(phone)
          .name(name)
          .address(address)
          .email(email)
          .build();
        Mockito.when(companyRepository.findById(id)).thenReturn(Optional.of(Company.builder()
          .id(1)
          .phone(oldPhone)
          .name(oldName)
          .address(oldAddress)
          .email(oldEmail)
          .build()));
        Mockito.when(companyRepository.save(Mockito.any(Company.class))).thenReturn(Company.builder()
          .id(1)
          .phone(phone)
          .name(name)
          .address(address)
          .email(email)
          .build());

        Optional<CompanyDTO> companyDtoOptional = companyCrudService.update(id, companyDTO);

        // Assert
        Assertions.assertThat(companyDtoOptional.isPresent()).isTrue();
        Assertions.assertThat(companyDtoOptional.get().getId()).isEqualTo(id);
        Assertions.assertThat(companyDtoOptional.get().getPhone()).isEqualTo(phone);
        Assertions.assertThat(companyDtoOptional.get().getName()).isEqualTo(name);
        Assertions.assertThat(companyDtoOptional.get().getAddress()).isEqualTo(address);
        Assertions.assertThat(companyDtoOptional.get().getEmail()).isEqualTo(email);
        Mockito.verify(companyRepository, Mockito.times(1)).findById(id);
        Mockito.verify(companyRepository, Mockito.times(1)).save(ArgumentMatchers.any(Company.class));
    }

    @Test
    void deleteById_shouldNotUpdateANotExistingCompany() {
        // Arrange
        Integer id = 1;
        String phone = "1234567890";
        String name = "Tech Corp";
        String address = "123 Tech Street";
        String email = "contact@techcorp.com";
        CompanyDTO companyDTO = CompanyDTO.builder()
          .phone(phone)
          .name(name)
          .address(address)
          .email(email)
          .build();
        Mockito.when(companyRepository.findById(id)).thenReturn(Optional.empty());

        Optional<CompanyDTO> companyDtoOptional = companyCrudService.update(id, companyDTO);

        // Assert
        Assertions.assertThat(companyDtoOptional.isEmpty()).isTrue();
        Mockito.verify(companyRepository, Mockito.times(1)).findById(id);
        Mockito.verify(companyRepository, Mockito.never()).save(ArgumentMatchers.any(Company.class));
    }

    @Test
    void findAll_shouldReturnListOfCompanyDTO() {
        // Arrange
        Company company1 = Company.builder().build();
        Company company2 = Company.builder().build();
        List<Company> companies = List.of(company1, company2);

        CompanyDTO companyDTO1 = CompanyDTO.builder().build();
        CompanyDTO companyDTO2 = CompanyDTO.builder().build();

        when(companyRepository.findAll()).thenReturn(companies);

        // Act
        List<CompanyDTO> result = companyCrudService.findAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals(companyDTO1, result.get(0));
        assertEquals(companyDTO2, result.get(1));
        verify(companyRepository, times(1)).findAll();
    }

    @Test
    void findById_shouldReturnCompanyDTOWhenPresent() {
        // Arrange
        Integer id = 1;
        Company company = Company.builder().build();
        CompanyDTO companyDTO = CompanyDTO.builder().build();

        when(companyRepository.findById(id)).thenReturn(Optional.of(company));

        // Act
        Optional<CompanyDTO> result = companyCrudService.findById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(companyDTO, result.get());
        verify(companyRepository, times(1)).findById(id);
    }

    @Test
    void findById_shouldReturnEmptyWhenNotPresent() {
        // Arrange
        Integer id = 1;
        when(companyRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<CompanyDTO> result = companyCrudService.findById(id);

        // Assert
        assertFalse(result.isPresent());
        verify(companyRepository, times(1)).findById(id);
    }

    @Test
    void deleteById_shouldDeleteAnExistingCompany() {
        // Arrange
        Integer id = 1;
        Mockito.when(companyRepository.findById(id)).thenReturn(Optional.of(Company.builder().build()));

        boolean isDeleted = companyCrudService.deleteById(id);

        // Assert
        Assertions.assertThat(isDeleted).isTrue();
        Mockito.verify(companyRepository, Mockito.times(1)).findById(id);
        Mockito.verify(companyRepository, Mockito.times(1)).delete(ArgumentMatchers.any(Company.class));
    }

    @Test
    void deleteById_shouldNotDeleteANotExistingCompany() {
        // Arrange
        Integer id = 1;
        Mockito.when(companyRepository.findById(id)).thenReturn(Optional.empty());

        boolean isDeleted = companyCrudService.deleteById(id);

        // Assert
        Assertions.assertThat(isDeleted).isFalse();
        Mockito.verify(companyRepository, Mockito.times(1)).findById(id);
        Mockito.verify(companyRepository, Mockito.never()).delete(ArgumentMatchers.any(Company.class));
    }
}