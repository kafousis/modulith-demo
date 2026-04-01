package com.demo.customers.api.events;

/**
 * Event representing the update of an existing customer.
 *
 * @param fullName the full name of the customer
 * @param email the email address of the customer
 */
public record CustomerUpdatedEvent(String fullName, String email) { }