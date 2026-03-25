package com.demo.customers.adapter.persistence.repository;

import com.demo.customers.adapter.persistence.entity.CustomerEntity;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for {@link CustomerEntity} operations.
 */
public interface CustomerRepository extends JpaRepository<@NonNull CustomerEntity, @NonNull Long> {

    /**
     * Checks if a customer exists with the specified VAT number.
     *
     * @param vatNumber the VAT number to check for existence
     * @return true if a customer with this VAT number exists, false otherwise
     */
    boolean existsByVatNumber(String vatNumber);
}