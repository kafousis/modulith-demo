package com.demo.customers.api.events;

/**
 * Event representing the creation of a new customer.
 *
 * @param fullName the full name of the customer
 * @param email the email address of the customer
 */
public record CustomerCreatedEvent(String fullName, String email) { }