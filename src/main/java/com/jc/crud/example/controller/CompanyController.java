package com.jc.crud.example.controller;

import com.jc.crud.example.model.CompanyDTO;
import com.jc.crud.example.service.CompanyCrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Validated
@RestController
@RequestMapping(CompanyController.API_COMPANIES)
@Tag(name = "Company API")
public class CompanyController {
  static final String API_COMPANIES = "/api/companies";

  private final CompanyCrudService companyCrudService;

  public CompanyController(CompanyCrudService companyCrudService) {
    this.companyCrudService = companyCrudService;
  }

  @Operation(summary = "Create a new company")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Company created successfully"),
    @ApiResponse(responseCode = "400", description = "Invalid input data")
  })
  @PostMapping
  public ResponseEntity<Void> createCompany(@Valid @RequestBody CompanyDTO companyDTO) {
    companyCrudService.insert(companyDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Operation(summary = "Update an existing company")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Company updated successfully"),
    @ApiResponse(responseCode = "404", description = "Company not found")
  })
  @PutMapping("/{id}")
  public ResponseEntity<CompanyDTO> updateCompany(@PathVariable("id") Integer id, @Valid @RequestBody CompanyDTO companyDTO) {
    return wrapResult(companyCrudService.update(id, companyDTO));
  }

  @Operation(summary = "Delete a company")
  @ApiResponses({
    @ApiResponse(responseCode = "204", description = "Company deleted successfully"),
    @ApiResponse(responseCode = "404", description = "Company not found")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCompany(@PathVariable("id") Integer id) {
    if(companyCrudService.deleteById(id)) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @Operation(summary = "Retrieve a single company by ID")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Company found"),
    @ApiResponse(responseCode = "404", description = "Company not found")
  })
  @GetMapping("/{id}")
  public ResponseEntity<CompanyDTO> getCompany(@PathVariable("id") Integer id) {
    return wrapResult(companyCrudService.findById(id));
  }

  @Operation(summary = "Retrieve all companies")
  @ApiResponse(responseCode = "200", description = "List of companies")
  @GetMapping
  public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
    return ResponseEntity.ok(companyCrudService.findAll());
  }

  private <T> ResponseEntity<T> wrapResult(Optional<T> result) {
    return result.map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }
}
