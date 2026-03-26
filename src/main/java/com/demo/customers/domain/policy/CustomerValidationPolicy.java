package com.demo.customers.domain.policy;

import com.demo.customers.domain.model.Address;
import com.demo.customers.domain.model.Customer;
import com.demo.customers.domain.model.CustomerType;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import lombok.experimental.UtilityClass;

/**
 * Basic validation policy for customers in the insurance system.
 * Enforces core insurance domain rules for individual and business customers.
 */
@UtilityClass
public final class CustomerValidationPolicy {

    private static final int MINIMUM_AGE_FOR_INSURANCE = 18;
    private static final Pattern EMAIL_PATTERN = 
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$", Pattern.CASE_INSENSITIVE);

    /**
     * Validates a customer against insurance domain rules.
     */
    public static void validateCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        
        validateEmail(customer.email());
        validateAddress(customer.address());
        validateVatNumber(customer.vatNumber());
        
        if (customer.type() == CustomerType.INDIVIDUAL) {
            validateIndividualCustomer(customer);
        } else if (customer.type() == CustomerType.BUSINESS) {
            validateBusinessCustomer(customer);
        }
    }

    /**
     * Validates an individual customer (requires birthdate and age check).
     */
    private static void validateIndividualCustomer(Customer customer) {
        if (StringUtils.isBlank(customer.firstName())) {
            throw new IllegalArgumentException("First name is required");
        }
        
        if (StringUtils.isBlank(customer.lastName())) {
            throw new IllegalArgumentException("Last name is required");
        }
        
        if (customer.birthDate() == null) {
            throw new IllegalArgumentException("Birth date is required for individual customers");
        }
        
        validateAge(customer.birthDate());
    }

    /**
     * Validates a business customer (requires company name)
     */
    private static void validateBusinessCustomer(Customer customer) {
        if (StringUtils.isBlank(customer.companyName())) {
            throw new IllegalArgumentException("Company name is required for business customers");
        }
    }

    /**
     * Validates VAT number format (basic check for non-empty).
     */
    private static void validateVatNumber(String vatNumber) {
        if (StringUtils.isBlank(vatNumber)) {
            throw new IllegalArgumentException("VAT number is required");
        }
    }

    /**
     * Validates email format.
     */
    private static void validateEmail(String email) {
        if (StringUtils.isBlank(email)) {
            throw new IllegalArgumentException("Email is required");
        }
        
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Email format is invalid");
        }
    }

    /**
     * Validates customer age against insurance minimum.
     */
    private static void validateAge(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("Birth date is required");
        }
        
        if (birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birth date cannot be in the future");
        }
        
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        
        if (age < MINIMUM_AGE_FOR_INSURANCE) {
            throw new IllegalArgumentException("Customer must be at least 18 years old");
        }
    }

    /**
     * Validates address completeness.
     */
    private static void validateAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Address is required");
        }
        
        if (StringUtils.isBlank(address.street())) {
            throw new IllegalArgumentException("Street is required");
        }
        
        if (StringUtils.isBlank(address.city())) {
            throw new IllegalArgumentException("City is required");
        }
        
        if (StringUtils.isBlank(address.postalCode())) {
            throw new IllegalArgumentException("Postal code is required");
        }
        
        if (StringUtils.isBlank(address.countryCode())) {
            throw new IllegalArgumentException("Country code is required");
        }
    }
}