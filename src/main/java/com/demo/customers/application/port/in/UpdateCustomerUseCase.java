package com.demo.customers.application.port.in;

import com.demo.customers.application.command.UpdateCustomerCommand;

public interface UpdateCustomerUseCase {
    /**
     * Updates a customer's information.
     *
     * @param command contains updated customer fields
     */
    void updateCustomer(UpdateCustomerCommand command);

}
