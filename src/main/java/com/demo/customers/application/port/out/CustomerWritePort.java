package com.demo.customers.application.port.out;

import java.util.Optional;

import com.demo.customers.domain.model.Customer;

/**
 * Output port for WRITE operations on customer data.
 * <p>
 *  Used by:  {@link com.demo.customers.application.service.CustomerService}
 * </p>
 */
public interface CustomerWritePort {

    /**
     * Persists a customer (creates new or updates existing).
     * 
     * @param customer the customer to save
     * @return the persisted customer with generated ID if new
     */
    Customer save(Customer customer);

    /**
     * Validates that no duplicate customer exists with the given VAT number.
     * Used during customer creation to enforce business rule uniqueness.
     * 
     * @param vatNumber the VAT number to check
     * @return true if a customer exists with this VAT number, false otherwise
     */
    boolean existsByVatNumber(String vatNumber);

    /**
     * Retrieves a customer by ID for modification operations.
     * 
     * @param id the customer ID
     * @return Optional containing the customer if found, empty otherwise
     */
    Optional<Customer> findById(Long id);
}