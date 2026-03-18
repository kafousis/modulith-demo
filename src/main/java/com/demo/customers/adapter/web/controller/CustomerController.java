package com.demo.customers.adapter.web.controller;

import java.util.List;

import com.demo.customers.adapter.web.dto.AddressRequest;
import com.demo.customers.adapter.web.dto.CreateCustomerRequest;
import com.demo.customers.adapter.web.dto.UpdateCustomerRequest;
import com.demo.customers.adapter.web.mapper.CustomerRequestMapper;
import com.demo.customers.application.port.in.ChangeCustomerAddressUseCase;
import com.demo.customers.application.port.in.CreateCustomerUseCase;
import com.demo.customers.application.port.in.UpdateCustomerUseCase;
import com.demo.customers.application.query.CustomerQueryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    private final CreateCustomerUseCase createUseCase;
    private final UpdateCustomerUseCase updateUseCase;
    private final ChangeCustomerAddressUseCase changeAddressUseCase;

    private final CustomerQueryService queryService;
    private final CustomerRequestMapper mapper;

    @PostMapping
    public Long createCustomer(@RequestBody CreateCustomerRequest request) {
        return createUseCase.createCustomer(mapper.toCommand(request));
    }

    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable Long id, @RequestBody UpdateCustomerRequest request) {
        updateUseCase.updateCustomer(mapper.toCommand(id, request));
    }

    @PutMapping("/{id}/address")
    public void changeAddress(@PathVariable Long id, @RequestBody AddressRequest request) {
        changeAddressUseCase.changeCustomerAddress(mapper.toCommand(id, request));
    }

    @GetMapping("/{id}")
    public Object getCustomer(@PathVariable Long id) {
        return queryService.getCustomer(id);
    }

    @GetMapping
    public List<?> listCustomers() {
        return queryService.listCustomers();
    }
}