package com.demo.customers.adapter.in.rest;

import com.demo.customers.application.exception.CustomerNotFoundException;
import com.demo.customers.domain.exception.VatNumberAlreadyInUseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class CustomersExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        String errorMessage = "Customer not found: " + ex.getMessage();
        log.error("Customer not found: {}", errorMessage, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(VatNumberAlreadyInUseException.class)
    public ResponseEntity<String> handleVatNumberAlreadyInUseException(VatNumberAlreadyInUseException ex) {
        String errorMessage = "VAT number already in use: " + ex.getMessage();
        log.error("VAT number already in use: {}", errorMessage, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception ex) {
        log.error("An error occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }
}
