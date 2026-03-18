package com.demo.customers.adapter.persistence.adapter;

import java.util.List;
import java.util.Optional;

import com.demo.customers.adapter.persistence.mapper.CustomerPersistenceMapper;
import com.demo.customers.adapter.persistence.repository.CustomerRepository;
import com.demo.customers.application.port.out.CustomerReadPort;
import com.demo.customers.domain.model.Customer;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

/**
 * Persistence adapter for READING customer data from the database.
 * <p>
 * This adapter implements the {@link CustomerReadPort} interface to provide
 * read-only access to customer data. It serves as a bridge between the domain
 * layer and the persistence layer, converting between domain entities and
 * database entities using the {@link CustomerPersistenceMapper}.
 * </p>
 */
@Repository
@AllArgsConstructor
public class CustomerReadAdapter implements CustomerReadPort {

    private final CustomerRepository customerRepository;
    private final CustomerPersistenceMapper mapper;

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

    /**
     * Retrieves all customers from the database.
     * 
     * @return a list of all customers in the system
     */
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
