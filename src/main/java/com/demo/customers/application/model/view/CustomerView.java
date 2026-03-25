package com.demo.customers.application.model.view;

import java.time.LocalDate;

import com.demo.customers.application.model.view.dto.AddressView;

/**
 * View object representing customer information for read operations.
 * Contains all customer details including personal information and address.
 *
 * @param id           unique customer identifier
 * @param firstName    customer's first name
 * @param lastName     customer's last name
 * @param companyName  company name (if applicable)
 * @param birthDate    customer's birthdate
 * @param vatNumber    VAT identification number
 * @param email        customer's email address
 * @param phoneNumber  customer's phone number
 * @param address      customer's address information
 * @param type         customer type classification
 */
public record CustomerView(
        Long id,
        String firstName,
        String lastName,
        String companyName,
        LocalDate birthDate,
        String vatNumber,
        String email,
        String phoneNumber,
        AddressView address,
        String type
) {}
