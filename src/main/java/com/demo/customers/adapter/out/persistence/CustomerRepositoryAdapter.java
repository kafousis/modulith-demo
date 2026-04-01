package com.demo.customers.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import com.demo.customers.adapter.out.persistence.entity.CustomerEntity;
import com.demo.customers.adapter.out.persistence.mapper.CustomerEntityMapper;
import com.demo.customers.adapter.out.persistence.repository.CustomerRepository;
import com.demo.customers.application.port.out.CustomerRepositoryPort;
import com.demo.customers.domain.model.Customer;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

/**
 * Adapter class that implements the CustomerRepositoryPort interface and interacts with the CustomerRepository.
 * It uses the CustomerEntityMapper to convert between Customer domain objects and CustomerEntity objects.
 */
@Repository
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {

    private final CustomerRepository customerRepository;
    private final CustomerEntityMapper customerEntityMapper;

    /**
     * Retrieves all customers from the database, converts them to domain objects, and returns them as a list.
     *
     * @return a list of Customer domain objects
     */
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll().stream()
                .map(customerEntityMapper::toDomain)
                .toList();
    }

    /**
     * Retrieves a customer by its ID, converts it to a domain object, and returns it wrapped in an Optional.
     *
     * @param id the ID of the customer to retrieve
     * @return an Optional containing the Customer domain object if found, or empty if not found
     */
    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id)
                .map(customerEntityMapper::toDomain);
    }

    /**
     * Retrieves a customer by its VAT number, converts it to a domain object, and returns it wrapped in an Optional.
     *
     * @param vatNumber the VAT number of the customer to retrieve
     * @return an Optional containing the Customer domain object if found, or empty if not found
     */
    @Override
    public boolean existsByVatNumber(String vatNumber) {
        return customerRepository.existsByVatNumber(vatNumber);
    }

    /**
     * Checks if a customer exists by its ID.
     *
     * @param id the ID of the customer to check
     * @return true if a customer with the given ID exists, false otherwise
     */
    @Override
    public boolean existsById(Long id) {
        return customerRepository.existsById(id);
    }

    /**
     * Saves a customer to the database. It converts the Customer domain object to a CustomerEntity,
     * saves it using the repository, and then converts the saved entity back to a domain object.
     *
     * @param customer the Customer domain object to save
     * @return the saved Customer domain object
     */
    @Override
    public Customer save(Customer customer) {
        CustomerEntity entity = customerEntityMapper.toEntity(customer);
        CustomerEntity saved = customerRepository.save(entity);
        return customerEntityMapper.toDomain(saved);
    }

    /**
     * Deletes a customer from the database by its ID.
     *
     * @param id the ID of the customer to delete
     */
    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
