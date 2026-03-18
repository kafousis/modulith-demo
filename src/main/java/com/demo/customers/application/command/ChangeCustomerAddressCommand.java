package com.demo.customers.application.command;

import com.demo.customers.application.command.input.AddressInput;

/**
 * Command to update a customer's address.
 *
 * @param customerId The unique identifier of the customer whose address is being updated
 * @param address    The new address information as {@link AddressInput} (required)
 */
public record ChangeCustomerAddressCommand(
        Long customerId,
        AddressInput address
) {}
