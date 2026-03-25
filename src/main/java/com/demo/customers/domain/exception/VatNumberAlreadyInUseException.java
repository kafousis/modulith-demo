package com.demo.customers.domain.exception;

/**
 * Exception thrown when attempting to create or update a customer with a VAT number that is already in use.
 */
public class VatNumberAlreadyInUseException extends RuntimeException {
    public VatNumberAlreadyInUseException(String message) {
        super(message);
    }
}
