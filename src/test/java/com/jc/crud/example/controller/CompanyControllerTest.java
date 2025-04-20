package com.jc.crud.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jc.crud.example.model.CompanyDTO;
import com.jc.crud.example.service.CompanyCrudService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CompanyControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CompanyCrudService companyCrudService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void testCreateCompany_OK() throws Exception {
    CompanyDTO request = CompanyDTO.builder()
      .id(1)
      .name("Test")
      .address("123 Street")
      .email("test@mail.com")
      .phone("1234567890")
      .build();
    mockMvc.perform(MockMvcRequestBuilders.post("/api/companies")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(request)))
      .andExpect(status().isCreated());
    Mockito.verify(companyCrudService, Mockito.times(1)).insert(Mockito.any(CompanyDTO.class));
  }

  @Test
  void testUpdateCompany_OK() throws Exception {
    CompanyDTO request = CompanyDTO.builder()
      .name("Updated")
      .address("456 Avenue")
      .email("updated@mail.com")
      .phone("0987654321")
      .build();
    when(companyCrudService.update(any(Integer.class), any(CompanyDTO.class)))
      .thenReturn(Optional.of(CompanyDTO.builder()
        .id(1)
        .name("Updated")
        .address("456 Avenue")
        .email("updated@mail.com")
        .phone("0987654321")
        .build()));

    mockMvc.perform(MockMvcRequestBuilders.put("/api/companies/1")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(request)))
      .andExpect(status().isOk());
    Mockito.verify(companyCrudService, Mockito.times(1)).update(any(Integer.class), any(CompanyDTO.class));
  }

  @Test
  void testUpdateCompany_NotFound() throws Exception {
    CompanyDTO request = CompanyDTO.builder()
      .name("Updated")
      .address("456 Avenue")
      .email("updated@mail.com")
      .phone("0987654321")
      .build();
    when(companyCrudService.update(any(Integer.class), any(CompanyDTO.class)))
      .thenReturn(Optional.empty());

    mockMvc.perform(MockMvcRequestBuilders.put("/api/companies/1")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(request)))
      .andExpect(status().isNotFound());
    Mockito.verify(companyCrudService, Mockito.times(1)).update(any(Integer.class), any(CompanyDTO.class));
  }

  @Test
  void testDeleteCompany_OK() throws Exception {
    when(companyCrudService.deleteById(1)).thenReturn(true);
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/companies/1"))
      .andExpect(status().isNoContent());
    Mockito.verify(companyCrudService, Mockito.times(1)).deleteById(1);
  }

  @Test
  void testDeleteCompany_NotFound() throws Exception {
    when(companyCrudService.deleteById(1)).thenReturn(false);
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/companies/1"))
      .andExpect(status().isNotFound());
    Mockito.verify(companyCrudService, Mockito.times(1)).deleteById(1);
  }

  @Test
  void testGetCompany_OK() throws Exception {
    when(companyCrudService.findById(1))
      .thenReturn(Optional.of(CompanyDTO.builder()
        .id(1)
        .name("Test")
        .address("123 Street")
        .email("test@mail.com")
        .phone("1234567890")
        .build()));

    mockMvc.perform(MockMvcRequestBuilders.get("/api/companies/1"))
      .andExpect(status().isOk());
    Mockito.verify(companyCrudService, Mockito.times(1)).findById(1);
  }

  @Test
  void testGetCompany_NotFound() throws Exception {
    when(companyCrudService.findById(1))
      .thenReturn(Optional.empty());

    mockMvc.perform(MockMvcRequestBuilders.get("/api/companies/1"))
      .andExpect(status().isNotFound());
    Mockito.verify(companyCrudService, Mockito.times(1)).findById(1);
  }

  @Test
  void testGetAllCompanies_OK() throws Exception {
    when(companyCrudService.findAll()).thenReturn(List.of(CompanyDTO.builder()
      .id(1)
      .name("Test")
      .address("123 Street")
      .email("test@mail.com")
      .phone("1234567890")
      .build()));
    mockMvc.perform(MockMvcRequestBuilders.get("/api/companies"))
      .andExpect(status().isOk());
    Mockito.verify(companyCrudService, Mockito.times(1)).findAll();
  }
}
