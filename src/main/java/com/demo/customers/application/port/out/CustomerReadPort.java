package com.demo.customers.application.port.out;

import java.util.List;
import java.util.Optional;

import com.demo.customers.domain.model.Customer;

/**
 * Output port for READ operations on customer data.
 * <p>
 *  Used by:  {@link com.demo.customers.application.query.CustomerQueryService}
 * </p>
 */
public interface CustomerReadPort {

    /**
     * Retrieves a customer by their unique identifier.
     * 
     * @param id the customer ID
     * @return Optional containing the customer if found, empty otherwise
     */
    Optional<Customer> findById(Long id);

    /**
     * Retrieves all customers from the system.
     * 
     * @return list of all customers (may be empty)
     */
    List<Customer> findAll();
}