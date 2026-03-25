package com.demo.customers.application.port.in;

import com.demo.customers.application.model.command.CustomerCommand;

public interface CreateCustomerUseCase {

    /**
     * Creates a new customer based on the provided command.
     *
     * @param command the command containing customer details
     * @return the ID of the newly created customer
     */
    Long createCustomer(CustomerCommand command);
}
