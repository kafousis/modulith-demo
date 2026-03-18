package com.demo.customers.adapter.web.dto;

import java.time.LocalDate;

public record CreateCustomerRequest(
        String firstName,
        String lastName,
        String companyName,
        LocalDate birthDate,
        String vatNumber,
        String email,
        String phoneNumber,
        AddressRequest address,
        String type
) {}