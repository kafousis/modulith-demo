package com.demo.customers.adapter.rest.dto;

import java.time.LocalDate;

public record CustomerRequest(
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