package com.demo.customers.application.service;

import com.demo.customers.application.command.ChangeCustomerAddressCommand;
import com.demo.customers.application.command.CreateCustomerCommand;
import com.demo.customers.application.command.UpdateCustomerCommand;
import com.demo.customers.application.exception.CustomerNotFoundException;
import com.demo.customers.application.exception.DuplicateCustomerException;
import com.demo.customers.application.port.in.ChangeCustomerAddressUseCase;
import com.demo.customers.application.port.in.CreateCustomerUseCase;
import com.demo.customers.application.port.in.UpdateCustomerUseCase;
import com.demo.customers.application.port.out.CustomerWritePort;
import com.demo.customers.application.service.mapper.CustomerCommandMapper;
import com.demo.customers.domain.event.CustomerAddressChangedEvent;
import com.demo.customers.domain.event.CustomerCreatedEvent;
import com.demo.customers.domain.event.CustomerUpdatedEvent;
import com.demo.customers.domain.model.Customer;
import com.demo.customers.domain.policy.CustomerValidationPolicy;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * Application service for managing customer WRITE operations.
 *
 * <p>Handles three main use cases: creating, updating, and changing customer addresses.
 * Implements domain-driven design by delegating command-to-domain mapping and persisting
 * through ports, while publishing domain events for eventual consistency.
 *
 * <p>All operations are transactional to ensure data consistency.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService implements CreateCustomerUseCase, UpdateCustomerUseCase, ChangeCustomerAddressUseCase {

    private final CustomerWritePort writePort;
    private final CustomerCommandMapper mapper;
    private final ApplicationEventPublisher events;

    /**
     * Creates a new customer.
     *
     * <p>Validates that no customer with the same VAT number exists, maps command to domain model,
     * persists the customer, and publishes a CustomerCreatedEvent.
     *
     * @param command the create customer command containing all required customer data
     * @return the ID of the newly created customer
     * @throws DuplicateCustomerException if a customer with the same VAT number already exists
     */
    @Override
    public Long createCustomer(CreateCustomerCommand command) {

        if (writePort.existsByVatNumber(command.vatNumber())) {
            throw new DuplicateCustomerException(
                    "Customer with VAT " + command.vatNumber() + " already exists."
            );
        }

        Customer customer = mapper.toDomain(command);
        CustomerValidationPolicy.validateCustomer(customer);

        Customer created = writePort.save(customer);
        events.publishEvent(new CustomerCreatedEvent(created.id()));
        return created.id();
    }

    /**
     * Updates an existing customer's information.
     *
     * <p>Retrieves the customer by ID, maps the update command to a new domain model,
     * persists the changes, and publishes a CustomerUpdatedEvent.
     *
     * @param command the update customer command containing the customer ID and updated fields
     * @throws CustomerNotFoundException if the customer with the given ID does not exist
     */
    @Override
    public void updateCustomer(UpdateCustomerCommand command) {
        Customer existing = writePort.findById(command.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer with ID " + command.id() + " not found.")
                );

        Customer updated = mapper.toDomain(existing, command);
        CustomerValidationPolicy.validateCustomer(updated);
        writePort.save(updated);

        events.publishEvent(new CustomerUpdatedEvent(updated.id()));
    }

    /**
     * Updates a customer's address.
     *
     * <p>Retrieves the customer, updates only the address field while preserving other data,
     * persists the change, and publishes a CustomerAddressChangedEvent.
     *
     * @param command the change address command containing the customer ID and new address
     * @throws CustomerNotFoundException if the customer with the given ID does not exist
     */
    @Override
    public void changeCustomerAddress(ChangeCustomerAddressCommand command) {
        Customer existing = writePort.findById(command.customerId())
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer with ID " + command.customerId() + " not found.")
                );

        Customer updated = mapper.toDomain(existing, command.address());
        CustomerValidationPolicy.validateCustomer(updated);
        writePort.save(updated);

        events.publishEvent(new CustomerAddressChangedEvent(updated.id(), updated.address()));
    }
}
