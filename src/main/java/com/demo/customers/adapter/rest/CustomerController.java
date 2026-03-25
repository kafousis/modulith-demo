package com.demo.customers.adapter.rest;

import java.util.List;

import com.demo.customers.adapter.rest.dto.CustomerRequest;
import com.demo.customers.adapter.rest.dto.CustomerResponse;
import com.demo.customers.adapter.rest.mapper.CustomerRequestMapper;
import com.demo.customers.adapter.rest.mapper.CustomerResponseMapper;
import com.demo.customers.application.model.command.CustomerCommand;
import com.demo.customers.application.model.view.CustomerView;
import com.demo.customers.application.service.CustomerService;

import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRequestMapper requestMapper;
    private final CustomerResponseMapper responseMapper;

    @GetMapping
    public ResponseEntity<@NonNull List<CustomerResponse>> listCustomers() {
        List<CustomerResponse> response = customerService.listCustomers().stream()
                .map(responseMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<@NonNull CustomerResponse> getCustomer(@PathVariable Long id) {
        CustomerView customerView = customerService.getCustomerById(id);
        CustomerResponse response = responseMapper.toResponse(customerView);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<@NonNull CustomerResponse> createCustomer(@RequestBody CustomerRequest request) {
        CustomerCommand command = requestMapper.toCommand(request);
        Long id = customerService.createCustomer(command);
        CustomerView customerView = customerService.getCustomerById(id);
        CustomerResponse response = responseMapper.toResponse(customerView);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<@NonNull Void> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest request) {
        CustomerCommand command = requestMapper.toCommand(request);
        customerService.updateCustomer(id, command);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<@NonNull Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }
}