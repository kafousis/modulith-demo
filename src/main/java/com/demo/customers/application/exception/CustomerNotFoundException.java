package com.demo.customers.application.exception;

/**
 * Exception thrown when a customer with the specified ID is not found.
 */
public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
