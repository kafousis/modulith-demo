package com.demo.customers.adapter.persistence.adapter;

import java.util.Optional;

import com.demo.customers.adapter.persistence.entity.CustomerEntity;
import com.demo.customers.adapter.persistence.mapper.CustomerPersistenceMapper;
import com.demo.customers.adapter.persistence.repository.CustomerRepository;
import com.demo.customers.application.port.out.CustomerWritePort;
import com.demo.customers.domain.model.Customer;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

/**
 * Persistence adapter for WRITING customer data to the database.
 * 
 * This adapter implements the {@link CustomerWritePort} interface to provide
 * write operations for customer data. It serves as a bridge between the domain
 * layer and the persistence layer, handling the conversion between domain entities
 * and database entities using the {@link CustomerPersistenceMapper}.
 */
@Repository
@AllArgsConstructor
public class CustomerWriteAdapter implements CustomerWritePort {

    private final CustomerRepository customerRepository;
    private final CustomerPersistenceMapper mapper;

    /**
     * Persists a customer to the database.
     *
     * @param customer the customer domain entity to save
     * @return the saved customer with updated persistence information
     */
    @Override
    public Customer save(Customer customer) {
        CustomerEntity entity = mapper.toEntity(customer);
        CustomerEntity saved = customerRepository.save(entity);
        return mapper.toDomain(saved);
    }

    /**
     * Checks if a customer with the given VAT number already exists.
     * 
     * @param vatNumber the VAT number to check for uniqueness
     * @return true if a customer with this VAT number exists, false otherwise
     */
    @Override
    public boolean existsByVatNumber(String vatNumber) {
        return customerRepository.existsByVatNumber(vatNumber);
    }

    /**
     * Retrieves a customer by its unique identifier.
     * 
     * @param id the unique identifier of the customer to find
     * @return an Optional containing the customer if found, empty otherwise
     */
    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id)
                .map(mapper::toDomain);
    }
}
