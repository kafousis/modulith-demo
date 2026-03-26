package com.demo.customers.adapter.in.rest;

import java.util.List;

import com.demo.customers.adapter.in.rest.dto.CustomerRequest;
import com.demo.customers.adapter.in.rest.dto.CustomerResponse;
import com.demo.customers.adapter.in.rest.mapper.CustomerRequestMapper;
import com.demo.customers.adapter.in.rest.mapper.CustomerResponseMapper;
import com.demo.customers.application.model.command.CustomerCommand;
import com.demo.customers.application.model.view.CustomerView;
import com.demo.customers.application.service.CustomerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

/**
 * REST API endpoints for managing customers.
 * <p>
 * - GET /customers: List all customers.
 * - GET /customers/{id}: Get a specific customer by ID.
 * - POST /customers: Create a new customer.
 * - PUT /customers/{id}: Update an existing customer.
 * - DELETE /customers/{id}: Delete a customer by ID.
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/rest/v1/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRequestMapper requestMapper;
    private final CustomerResponseMapper responseMapper;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> listCustomers() {
        List<CustomerResponse> response = customerService.listCustomers().stream()
                .map(responseMapper::toResponse)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable @Min(1) Long id) {
        CustomerView customerView = customerService.getCustomerById(id);
        CustomerResponse response = responseMapper.toResponse(customerView);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest request) {
        CustomerCommand command = requestMapper.toCommand(request);
        Long id = customerService.createCustomer(command);
        CustomerView customerView = customerService.getCustomerById(id);
        CustomerResponse response = responseMapper.toResponse(customerView);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(@PathVariable @Min(1) Long id, @Valid @RequestBody CustomerRequest request) {
        CustomerCommand command = requestMapper.toCommand(request);
        customerService.updateCustomer(id, command);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable @Min(1) Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}