package com.demo.customers.application.port.in;

import com.demo.customers.application.command.CreateCustomerCommand;

public interface CreateCustomerUseCase {

    /**
     * Creates a new customer based on the provided command.
     *
     * @param command the creation intent
     * @return the newly created customer ID
     */
    Long createCustomer(CreateCustomerCommand command);
}
