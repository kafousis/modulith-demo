package com.demo.customers.application.query.view.dto;

/**
 * View object representing address information.
 * Contains complete address details for customer location.
 *
 * @param street      street address with number and name
 * @param city        city or municipality name
 * @param postalCode  postal or ZIP code
 * @param region      state, province, or region
 * @param countryCode ISO country code (e.g., "US", "GB")
 */
public record AddressView(
        String street,
        String city,
        String postalCode,
        String region,
        String countryCode
) {}