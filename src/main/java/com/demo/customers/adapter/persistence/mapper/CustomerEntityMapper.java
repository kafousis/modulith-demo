package com.demo.customers.adapter.persistence.mapper;

import com.demo.customers.adapter.persistence.entity.CustomerEntity;
import com.demo.customers.domain.model.Customer;

import org.mapstruct.Mapper;

/**
 * Mapper for converting between domain and persistence layer representations of customers.
 */
@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

    /**
     * Converts a persistence entity to a domain model.
     *
     * @param entity the customer persistence entity to convert
     * @return the corresponding domain model customer
     */
    Customer toDomain(CustomerEntity entity);

    /**
     * Converts a domain model to a persistence entity.
     *
     * @param customer the domain model customer to convert
     * @return the corresponding persistence entity
     */
    CustomerEntity toEntity(Customer customer);

    /**
     * Converts a domain address to a persistence address entity.
     *
     * @param address the domain address to convert
     * @return the corresponding persistence address entity
     */
    com.demo.customers.adapter.persistence.entity.Address toEntityAddress(com.demo.customers.domain.model.Address address);

    /**
     * Converts a persistence address entity to a domain address.
     *
     * @param address the persistence address entity to convert
     * @return the corresponding domain address
     */
    com.demo.customers.domain.model.Address toDomainAddress(com.demo.customers.adapter.persistence.entity.Address address);

    /**
     * Converts a persistence customer type enum to a domain customer type enum.
     *
     * @param type the persistence customer type to convert
     * @return the corresponding domain customer type
     */
    com.demo.customers.domain.model.CustomerType toDomainCustomerType(com.demo.customers.adapter.persistence.entity.CustomerType type);

    /**
     * Converts a domain customer type enum to a persistence customer type enum.
     *
     * @param type the domain customer type to convert
     * @return the corresponding persistence customer type
     */
    com.demo.customers.adapter.persistence.entity.CustomerType toEntityCustomerType(com.demo.customers.domain.model.CustomerType type);
}