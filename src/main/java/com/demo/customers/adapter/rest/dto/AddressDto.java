package com.demo.customers.adapter.rest.dto;

/**
 * Data Transfer Object for Address information.
 *
 * @param street     the street address
 * @param city       the city of the address
 * @param postalCode the postal code of the address
 * @param region     the region or state of the address
 * @param countryCode the ISO country code of the address
 */
public record AddressDto(
        String street,
        String city,
        String postalCode,
        String region,
        String countryCode
) {}