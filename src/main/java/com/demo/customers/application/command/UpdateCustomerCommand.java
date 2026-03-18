package com.demo.customers.application.command;

import java.time.LocalDate;

import com.demo.customers.application.command.input.AddressInput;
import com.demo.customers.application.command.input.CustomerTypeInput;

/**
 * Command to update an existing customer's information.
 *
 * @param id           Unique identifier of the customer to update (required)
 * @param firstName    Customer's first name (required for INDIVIDUAL customers, optional for BUSINESS)
 * @param lastName     Customer's last name (required for INDIVIDUAL customers, optional for BUSINESS)
 * @param companyName  Company name (required for BUSINESS customers, null for INDIVIDUAL)
 * @param birthDate    Customer's birthdate in ISO format (required for INDIVIDUAL, null for BUSINESS)
 * @param vatNumber    VAT or tax identification number (required for all customer types)
 * @param email        Customer's email address (required for all customer types)
 * @param phoneNumber  Customer's phone number (required for all customer types)
 * @param address      Customer's address information as {@link AddressInput} (required for all)
 * @param type         Customer type: INDIVIDUAL or COMPANY (required for all)
 */
public record UpdateCustomerCommand(
        Long id,
        String firstName,
        String lastName,
        String companyName,
        LocalDate birthDate,
        String vatNumber,
        String email,
        String phoneNumber,
        AddressInput address,
        CustomerTypeInput type
) {}
