package com.demo.customers.domain.event;

import com.demo.customers.domain.model.Address;

/**
 * Domain event published when a customer's address is changed in the insurance system.
 *
 * @param customerId The unique identifier of the customer whose address was changed.
 * @param newAddress The new address information for the customer.
 */
public record CustomerAddressChangedEvent(Long customerId, Address newAddress) { }