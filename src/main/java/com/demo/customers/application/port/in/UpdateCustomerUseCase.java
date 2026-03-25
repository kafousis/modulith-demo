package com.demo.customers.application.port.in;

import com.demo.customers.application.model.command.CustomerCommand;

public interface UpdateCustomerUseCase {

    /**
     * Updates an existing customer with the provided details.
     *
     * @param id      the ID of the customer to update
     * @param command the command containing updated customer details
     */
    void updateCustomer(Long id, CustomerCommand command);
}