package com.demo.customers.adapter.rest.mapper;

import com.demo.customers.adapter.rest.dto.AddressDto;
import com.demo.customers.adapter.rest.dto.CustomerRequest;
import com.demo.customers.application.model.command.CustomerCommand;
import com.demo.customers.application.model.command.input.AddressInput;
import com.demo.customers.application.model.command.input.CustomerTypeInput;

import org.springframework.stereotype.Component;

@Component
public class CustomerRequestMapper {

    public CustomerCommand toCommand(CustomerRequest request) {
        return new CustomerCommand(
                request.firstName(),
                request.lastName(),
                request.companyName(),
                request.birthDate(),
                request.vatNumber(),
                request.email(),
                request.phoneNumber(),
                toAddressInput(request.address()),
                toTypeInput(request.type())
        );
    }

    private AddressInput toAddressInput(AddressDto request) {
        return new AddressInput(
                request.street(),
                request.city(),
                request.postalCode(),
                request.region(),
                request.countryCode()
        );
    }

    private CustomerTypeInput toTypeInput(String type) {
        return CustomerTypeInput.valueOf(type.toUpperCase());
    }
}