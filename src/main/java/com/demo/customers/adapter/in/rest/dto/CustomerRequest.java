package com.demo.customers.adapter.in.rest.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for creating or updating a customer.
 *
 * @param firstName   the first name of the customer
 * @param lastName    the last name of the customer
 * @param companyName the company name of the customer
 * @param birthDate   the birthdate of the customer
 * @param vatNumber   the VAT number of the customer
 * @param email       the email address of the customer
 * @param phoneNumber the phone number of the customer
 * @param address     the address of the customer
 * @param type        the type of the customer
 */
public record CustomerRequest(
        String firstName,
        String lastName,
        String companyName,
        @NotNull LocalDate birthDate,
        @NotBlank  String vatNumber,
        @Email String email,
        @NotBlank String phoneNumber,
        @NotNull AddressDto address,
        @NotBlank String type
) {}