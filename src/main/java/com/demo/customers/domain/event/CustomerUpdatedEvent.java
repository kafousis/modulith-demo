package com.demo.customers.domain.event;

/**
 * Domain event published when an existing customer's information is updated in the insurance system.
 *
 * @param customerId The unique identifier of the customer whose information was updated.
 */
public record CustomerUpdatedEvent(Long customerId) { }