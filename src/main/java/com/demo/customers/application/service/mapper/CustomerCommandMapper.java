package com.demo.customers.application.service.mapper;

import org.springframework.stereotype.Component;
import java.time.Instant;

import com.demo.customers.application.command.CreateCustomerCommand;
import com.demo.customers.application.command.UpdateCustomerCommand;
import com.demo.customers.application.command.input.AddressInput;
import com.demo.customers.application.command.input.CustomerTypeInput;
import com.demo.customers.domain.model.Address;
import com.demo.customers.domain.model.Customer;
import com.demo.customers.domain.model.CustomerType;

/**
 * Mapper component that converts commands and DTOs to domain models.
 */
@Component
public class CustomerCommandMapper {

    /**
     * Converts a CreateCustomerCommand to a Customer domain model.
     *
     * @param cmd the create command
     * @return a new Customer domain model with generated timestamps (id is null for new customers)
     */
    public Customer toDomain(CreateCustomerCommand cmd) {
        return new Customer(
                null,
                cmd.firstName(),
                cmd.lastName(),
                cmd.companyName(),
                cmd.birthDate(),
                cmd.vatNumber(),
                cmd.email(),
                cmd.phoneNumber(),
                toAddress(cmd.address()),
                toCustomerType(cmd.type()),
                Instant.now(),
                Instant.now()
        );
    }

    /**
     * Converts an UpdateCustomerCommand to a Customer domain model.
     *
     * <p>Preserves the original customer's ID and creation timestamp,
     * updates all other fields and sets updatedAt to current time.
     *
     * @param existing the current customer
     * @param cmd the update command
     * @return an updated Customer domain model
     */
    public Customer toDomain(Customer existing, UpdateCustomerCommand cmd) {
        return new Customer(
                existing.id(),
                cmd.firstName(),
                cmd.lastName(),
                cmd.companyName(),
                cmd.birthDate(),
                cmd.vatNumber(),
                cmd.email(),
                cmd.phoneNumber(),
                toAddress(cmd.address()),
                toCustomerType(cmd.type()),
                existing.createdAt(),
                Instant.now()
        );
    }

    /**
     * Converts an AddressInput to a Customer domain model.
     *
     * <p>Preserves all existing customer data except the address field,
     * used for address-specific updates. Updates the timestamp.
     *
     * @param existing the current customer
     * @param input the new address input
     * @return a Customer domain model with updated address
     */
    public Customer toDomain(Customer existing, AddressInput input) {
        return new Customer(
                existing.id(),
                existing.firstName(),
                existing.lastName(),
                existing.companyName(),
                existing.birthDate(),
                existing.vatNumber(),
                existing.email(),
                existing.phoneNumber(),
                toAddress(input),
                existing.type(),
                existing.createdAt(),
                Instant.now()
        );
    }

    /**
     * Converts AddressInput DTO to Address domain model.
     *
     * @param input the address input DTO
     * @return a new Address domain model
     */
    private Address toAddress(AddressInput input) {
        return new Address(
                input.street(),
                input.city(),
                input.postalCode(),
                input.region(),
                input.countryCode()
        );
    }

    /**
     * Converts CustomerTypeInput enum to CustomerType enum.
     *
     * @param input the customer type input
     * @return the corresponding domain customer type
     */
    private CustomerType toCustomerType(CustomerTypeInput input) {
        return switch (input) {
            case INDIVIDUAL -> CustomerType.INDIVIDUAL;
            case BUSINESS -> CustomerType.BUSINESS;
        };
    }
}