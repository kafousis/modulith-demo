package com.demo.customers.domain.model;

/**
 * Represents a physical address.
 *
 * @param street      Street name and number.
 * @param city        City name.
 * @param postalCode  Postal code.
 * @param region      Region, state, or province.
 * @param countryCode ISO country code.
 */
public record Address(
        String street,
        String city,
        String postalCode,
        String region,
        String countryCode
) {}