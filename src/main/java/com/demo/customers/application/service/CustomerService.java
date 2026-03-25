package com.demo.customers.application.service;

import java.time.Instant;
import java.util.List;

import com.demo.customers.application.exception.CustomerNotFoundException;
import com.demo.customers.application.model.command.CustomerCommand;
import com.demo.customers.application.model.view.CustomerView;
import com.demo.customers.application.port.in.DeleteCustomerUseCase;
import com.demo.customers.application.port.in.GetCustomerUseCase;
import com.demo.customers.application.port.in.ListCustomersUseCase;
import com.demo.customers.application.port.out.CustomerRepositoryPort;
import com.demo.customers.application.port.in.CreateCustomerUseCase;
import com.demo.customers.application.port.in.UpdateCustomerUseCase;
import com.demo.customers.application.service.mapper.CustomerCommandMapper;
import com.demo.customers.application.service.mapper.CustomerViewMapper;
import com.demo.customers.domain.event.CustomerCreatedEvent;
import com.demo.customers.domain.exception.VatNumberAlreadyInUseException;
import com.demo.customers.domain.model.Customer;
import com.demo.customers.domain.policy.CustomerValidationPolicy;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService implements
        CreateCustomerUseCase,
        GetCustomerUseCase,
        UpdateCustomerUseCase,
        DeleteCustomerUseCase,
        ListCustomersUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;
    private final ApplicationEventPublisher eventPublisher;

    private final CustomerCommandMapper commandMapper;
    private final CustomerViewMapper viewMapper;

    @Override
    public Long createCustomer(CustomerCommand command) {

        if (customerRepositoryPort.existsByVatNumber(command.vatNumber())) {
            throw new VatNumberAlreadyInUseException(
                    "Customer with VAT " + command.vatNumber() + " already exists."
            );
        }

        Instant now = Instant.now();
        Customer customer = commandMapper.toDomain(command, now);
        CustomerValidationPolicy.validateCustomer(customer);

        Customer created = customerRepositoryPort.save(customer);
        eventPublisher.publishEvent(new CustomerCreatedEvent(created.id()));
        return created.id();
    }

    @Override
    public CustomerView getCustomerById(Long id) {
        return customerRepositoryPort.findById(id)
                .map(viewMapper::toView)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found."));
    }

    @Override
    public void updateCustomer(Long id, CustomerCommand command) {

        Customer existing = customerRepositoryPort.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found."));

        Instant now = Instant.now();
        Customer updated = commandMapper.toDomain(existing, command, now);
        CustomerValidationPolicy.validateCustomer(updated);
        customerRepositoryPort.save(updated);
    }

    @Override
    public void deleteCustomerById(Long id) {
        if (!customerRepositoryPort.existsById(id)) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
        }
        customerRepositoryPort.deleteById(id);
    }

    @Override
    public List<CustomerView> listCustomers() {
        return customerRepositoryPort.findAll()
                .stream()
                .map(viewMapper::toView)
                .toList();
    }
}
