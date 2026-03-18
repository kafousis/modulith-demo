package com.demo.customers.application.port.in;

import com.demo.customers.application.command.ChangeCustomerAddressCommand;

public interface ChangeCustomerAddressUseCase {
    /**
     * Updates only the address of an existing customer.
     *
     * @param command contains the customer ID and the new address
     */
    void changeCustomerAddress(ChangeCustomerAddressCommand command);
}
