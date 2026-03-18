package com.demo.customers.adapter.web.mapper;

import com.demo.customers.adapter.web.dto.AddressRequest;
import com.demo.customers.adapter.web.dto.CreateCustomerRequest;
import com.demo.customers.adapter.web.dto.UpdateCustomerRequest;
import com.demo.customers.application.command.ChangeCustomerAddressCommand;
import com.demo.customers.application.command.CreateCustomerCommand;
import com.demo.customers.application.command.UpdateCustomerCommand;
import com.demo.customers.application.command.input.AddressInput;
import com.demo.customers.application.command.input.CustomerTypeInput;

import org.springframework.stereotype.Component;

@Component
public class CustomerRequestMapper {

    public CreateCustomerCommand toCommand(CreateCustomerRequest request) {
        return new CreateCustomerCommand(
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

    public UpdateCustomerCommand toCommand(Long id, UpdateCustomerRequest request) {
        return new UpdateCustomerCommand(
                id,
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

    public ChangeCustomerAddressCommand toCommand(Long id, AddressRequest request) {
        return new ChangeCustomerAddressCommand(
                id,
                toAddressInput(request)
        );
    }

    private AddressInput toAddressInput(AddressRequest request) {
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