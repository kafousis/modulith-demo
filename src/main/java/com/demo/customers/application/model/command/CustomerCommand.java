package com.demo.customers.application.model.command;

import java.time.LocalDate;

import com.demo.customers.application.model.command.input.AddressInput;
import com.demo.customers.application.model.command.input.CustomerTypeInput;

/**
 * Command object for creating or updating a customer.
 *
 * @param firstName    customer's first name
 * @param lastName     customer's last name
 * @param companyName  company name
 * @param birthDate    customer's birthdate
 * @param vatNumber    VAT identification number
 * @param email        customer's email address
 * @param phoneNumber  customer's phone number
 * @param address      customer's address information
 * @param type         customer type classification
 */
public record CustomerCommand(
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
