package com.demo.customers.application.command.input;

/**
 * Data Transfer Object (DTO) for address information in command input.
 *
 * @param street       Street name and number (e.g., "123 Main Street")
 * @param city         City or town name (e.g., "New York")
 * @param postalCode   Postal or ZIP code (e.g., "10001")
 * @param region       Region, state, or province (e.g., "NY") - optional
 * @param countryCode  ISO 3166-1 alpha-2 country code (e.g., "US")
 */
public record AddressInput(
        String street,
        String city,
        String postalCode,
        String region,
        String countryCode
) {}