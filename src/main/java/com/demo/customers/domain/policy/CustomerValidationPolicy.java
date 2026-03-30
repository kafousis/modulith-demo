package com.demo.customers.domain.policy;

import com.demo.customers.domain.model.Customer;
import com.demo.customers.domain.model.CustomerType;

import java.time.LocalDate;
import java.time.Period;

import org.apache.commons.lang3.StringUtils;

import lombok.experimental.UtilityClass;

/**
 * Basic validation policy for customers in the insurance system.
 * Enforces core insurance domain rules for individual and business customers.
 */
@UtilityClass
public final class CustomerValidationPolicy {

    private static final int MINIMUM_AGE_FOR_INSURANCE = 18;

    /**
     * Validates a customer against insurance domain rules.
     */
    public static void validateCustomer(Customer customer) {
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
            throw new IllegalArgumentException("First name is required for individual customers");
        }
        
        if (StringUtils.isBlank(customer.lastName())) {
            throw new IllegalArgumentException("Last name is required for individual customers");
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
}