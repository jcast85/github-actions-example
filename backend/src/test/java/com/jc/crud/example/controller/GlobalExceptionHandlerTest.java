package com.jc.crud.example.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

  @InjectMocks
  private GlobalExceptionHandler globalExceptionHandler;

  @Test
  void handleGenericException_ShouldReturnInternalServerError() {
    // Arrange
    Exception exception = new RuntimeException("Test exception");

    // Act
    ResponseEntity<Void> response = globalExceptionHandler.handleGenericException(exception);

    // Assert
    Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    Assertions.assertNull(response.getBody());
  }

  @Test
  void handleGenericException_WithNullPointerException_ShouldReturnInternalServerError() {
    // Arrange
    Exception exception = new NullPointerException("Null pointer exception");

    // Act
    ResponseEntity<Void> response = globalExceptionHandler.handleGenericException(exception);

    // Assert
    Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    Assertions.assertNull(response.getBody());
  }

  @Test
  void handleGenericException_WithIllegalArgumentException_ShouldReturnInternalServerError() {
    // Arrange
    Exception exception = new IllegalArgumentException("Invalid argument");

    // Act
    ResponseEntity<Void> response = globalExceptionHandler.handleGenericException(exception);

    // Assert
    Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    Assertions.assertNull(response.getBody());
  }
}