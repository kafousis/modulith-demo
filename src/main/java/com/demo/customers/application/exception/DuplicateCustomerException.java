package com.demo.customers.application.exception;

/**
 * Exception thrown when attempting to create a customer that already exists.
 */
public class DuplicateCustomerException extends RuntimeException {
    public DuplicateCustomerException(String message) {
        super(message);
    }
}
