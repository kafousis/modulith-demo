package com.demo.customers.application.service.mapper;

import org.mapstruct.Mapper;
import java.time.Instant;

import com.demo.customers.application.model.command.CustomerCommand;
import com.demo.customers.application.model.command.input.AddressInput;
import com.demo.customers.application.model.command.input.CustomerTypeInput;
import com.demo.customers.domain.model.Address;
import com.demo.customers.domain.model.Customer;
import com.demo.customers.domain.model.CustomerType;

/**
 * Mapper component that converts commands and DTOs to domain models.
 */
@Mapper(componentModel = "spring")
public interface CustomerCommandMapper {

    /**
     * Converts a CustomerCommand to a Customer domain model for creation.
     *
     * @param command   the customer command containing input data
     * @param createdAt the timestamp when the customer is created
     * @return a new Customer domain model ready for persistence
     */
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

    /**
     * Converts a CustomerCommand to a Customer domain model for update.
     *
     * @param existing  the existing customer to be updated
     * @param command   the customer command containing updated data
     * @param updatedAt the timestamp when the customer is updated
     * @return an updated Customer domain model ready for persistence
     */
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

    /**
     * Converts AddressInput DTO to Address domain model.
     *
     * @param input the address input DTO
     * @return a new Address domain model
     */
    Address toAddress(AddressInput input);

    /**
     * Converts CustomerTypeInput enum to CustomerType enum.
     *
     * @param input the customer type input
     * @return the corresponding domain customer type
     */
    CustomerType toCustomerType(CustomerTypeInput input);
}