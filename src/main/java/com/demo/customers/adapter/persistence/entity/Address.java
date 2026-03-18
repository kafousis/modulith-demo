package com.demo.customers.adapter.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JPA embeddable class representing an address.
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    
    /**
     * Street address including number and street name.
     * First line of the postal address.
     */
    @Column
    private String street;
    
    /**
     * City or locality name.
     * The city where the address is located.
     */
    @Column
    private String city;
    
    /**
     * Postal or ZIP code.
     * Used for mail sorting and delivery.
     */
    @Column(name = "postal_code")
    private String postalCode;
    
    /**
     * Region, state, or province.
     * Administrative division within the country.
     */
    @Column
    private String region;
    
    /**
     * Country code following ISO 3166-1 alpha-2 standard.
     * Two-letter country identifier (e.g., US, CA, DE).
     */
    @Column(name = "country_code")
    private String countryCode;
}
