package com.demo.customers.domain.event;

/**
 * Domain event published when a new customer is created in the insurance system.
 *
 * @param customerId The unique identifier of the newly created customer.
 */
public record CustomerCreatedEvent(Long customerId) { }