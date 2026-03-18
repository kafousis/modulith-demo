package com.demo.customers.domain.model;

import java.time.Instant;
import java.time.LocalDate;

/**
 * Represents a customer in the insurance context.
 * <p>
 * This record encapsulates customer details such as personal information,
 * company data, contact information, address, customer type, and timestamps.
 * </p>
 *
 * @param id           Unique identifier for the customer.
 * @param firstName    Customer's first name (for individuals).
 * @param lastName     Customer's last name (for individuals).
 * @param companyName  Company name (for business customers).
 * @param birthDate    Customer's birthdate (for individuals).
 * @param vatNumber    VAT number
 * @param email        Customer's email address.
 * @param phoneNumber  Customer's phone number.
 * @param address      Customer's address information.
 * @param type         Type of customer (individual or business).
 * @param createdAt    Timestamp when the customer was created.
 * @param updatedAt    Timestamp when the customer was last updated.
 */
public record Customer(
        Long id,
        String firstName,
        String lastName,
        String companyName,
        LocalDate birthDate,
        String vatNumber,
        String email,
        String phoneNumber,
        Address address,
        CustomerType type,
        Instant createdAt,
        Instant updatedAt
) {}