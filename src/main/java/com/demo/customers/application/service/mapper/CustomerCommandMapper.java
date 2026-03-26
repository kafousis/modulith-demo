package com.demo.customers.application.service.mapper;

import org.mapstruct.Mapper;
import java.time.Instant;

import com.demo.customers.application.model.command.CustomerCommand;
import com.demo.customers.application.model.command.input.AddressInput;
import com.demo.customers.application.model.command.input.CustomerTypeInput;
import com.demo.customers.domain.model.Address;
import com.demo.customers.domain.model.Customer;
import com.demo.customers.domain.model.CustomerType;

@Mapper(componentModel = "spring")
public interface CustomerCommandMapper {

    default Customer toDomain(CustomerCommand command, Instant createdAt) {
        return new Customer(
                null,
                command.firstName(),
                command.lastName(),
                command.companyName(),
                command.birthDate(),
                command.vatNumber(),
                command.email(),
                command.phoneNumber(),
                toAddress(command.address()),
                toCustomerType(command.type()),
                createdAt,
                createdAt
        );
    }

    default Customer toDomain(Customer existing, CustomerCommand command, Instant updatedAt) {
        return new Customer(
                existing.id(),
                command.firstName(),
                command.lastName(),
                command.companyName(),
                command.birthDate(),
                command.vatNumber(),
                command.email(),
                command.phoneNumber(),
                toAddress(command.address()),
                toCustomerType(command.type()),
                existing.createdAt(),
                updatedAt
        );
    }

    Address toAddress(AddressInput input);

    CustomerType toCustomerType(CustomerTypeInput input);
}