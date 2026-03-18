package com.demo.customers.adapter.web.dto;

public record AddressRequest(
        String street,
        String city,
        String postalCode,
        String region,
        String countryCode
) {}