package com.demo.customers.adapter.rest.dto;

import java.time.LocalDate;

/**
 * DTO for returning customer information in API responses.
 *
 * @param id          the unique identifier of the customer
 * @param firstName   the first name of the customer
 * @param lastName    the last name of the customer
 * @param companyName the company name of the customer (optional)
 * @param birthDate   the birthdate of the customer (optional)
 * @param vatNumber   the VAT number of the customer (optional)
 * @param email       the email address of the customer (optional)
 * @param phoneNumber the phone number of the customer (optional)
 * @param address     the address of the customer (optional)
 * @param type        the type of the customer (e.g., "individual" or "company")
 */
public record CustomerResponse(
        Long id,
        String firstName,
        String lastName,
        String companyName,
        LocalDate birthDate,
        String vatNumber,
        String email,
        String phoneNumber,
        AddressDto address,
        String type
) {}