package com.demo.customers.adapter.rest.dto;

public record AddressDto(
        String street,
        String city,
        String postalCode,
        String region,
        String countryCode
) {}