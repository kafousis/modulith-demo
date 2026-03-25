package com.demo.customers.application.port.out;

import java.util.List;
import java.util.Optional;

import com.demo.customers.domain.model.Customer;

public interface CustomerRepositoryPort {

    /**
     * Retrieves all customers from the system.
     *
     * @return list of all customers (maybe empty)
     */
    List<Customer> findAll();

    /**
     * Retrieves a customer by their unique identifier.
     *
     * @param id the customer ID
     * @return Optional containing the customer if found, empty otherwise
     */
    Optional<Customer> findById(Long id);

    /**
     * Validates that no duplicate customer exists with the given VAT number.
     * Used during customer creation to enforce business rule uniqueness.
     *
     * @param vatNumber the VAT number to check
     * @return true if a customer exists with this VAT number, false otherwise
     */
    boolean existsByVatNumber(String vatNumber);

    /**
     * Checks if a customer exists by their unique identifier.
     *
     * @param id the customer ID to check
     * @return true if a customer exists with this ID, false otherwise
     */
    boolean existsById(Long id);

    /**
     * Persists a customer (creates new or updates existing).
     *
     * @param customer the customer to save
     * @return the persisted customer with generated ID if new
     */
    Customer save(Customer customer);

    /**
     * Deletes a customer by their unique identifier.
     *
     * @param id the customer ID to delete
     */
    void deleteById(Long id);
}