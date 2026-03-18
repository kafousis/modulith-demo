package com.demo.customers.adapter.persistence.mapper;

import com.demo.customers.adapter.persistence.entity.CustomerEntity;
import com.demo.customers.domain.model.Customer;

import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between domain and persistence layer representations of customers.
 */
@Component
public class CustomerPersistenceMapper {

    /**
     * Converts a persistence entity to a domain model.
     * 
     * @param entity the customer persistence entity to convert
     * @return the corresponding domain model customer
     */
    public Customer toDomain(CustomerEntity entity) {
        return new Customer(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getCompanyName(),
                entity.getBirthDate(),
                entity.getVatNumber(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                toDomainAddress(entity.getAddress()),
                toDomainCustomerType(entity.getType()),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    /**
     * Converts a domain model to a persistence entity.
     * 
     * @param customer the domain model customer to convert
     * @return the corresponding persistence entity
     */
    public CustomerEntity toEntity(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.id())
                .firstName(customer.firstName())
                .lastName(customer.lastName())
                .companyName(customer.companyName())
                .birthDate(customer.birthDate())
                .vatNumber(customer.vatNumber())
                .email(customer.email())
                .phoneNumber(customer.phoneNumber())
                .address(toEntityAddress(customer.address()))
                .type(toEntityCustomerType(customer.type()))
                .createdAt(customer.createdAt())
                .updatedAt(customer.updatedAt())
                .build();
    }

    /**
     * Converts a domain address to a persistence address entity.
     * 
     * @param address the domain address to convert
     * @return the corresponding persistence address entity
     */
    private com.demo.customers.adapter.persistence.entity.Address toEntityAddress(com.demo.customers.domain.model.Address address) {
        return com.demo.customers.adapter.persistence.entity.Address.builder()
                .street(address.street())
                .city(address.city())
                .postalCode(address.postalCode())
                .region(address.region())
                .countryCode(address.countryCode())
                .build();
    }

    /**
     * Converts a persistence address entity to a domain address.
     * 
     * @param address the persistence address entity to convert
     * @return the corresponding domain address
     */
    private com.demo.customers.domain.model.Address toDomainAddress(com.demo.customers.adapter.persistence.entity.Address address) {
        return new com.demo.customers.domain.model.Address(
                address.getStreet(),
                address.getCity(),
                address.getPostalCode(),
                address.getRegion(),
                address.getCountryCode()
        );
    }

    /**
     * Converts a persistence customer type enum to a domain customer type enum.
     * 
     * @param type the persistence customer type to convert
     * @return the corresponding domain customer type
     */
    private com.demo.customers.domain.model.CustomerType toDomainCustomerType(com.demo.customers.adapter.persistence.entity.CustomerType type) {
        return switch (type) {
            case INDIVIDUAL -> com.demo.customers.domain.model.CustomerType.INDIVIDUAL;
            case BUSINESS -> com.demo.customers.domain.model.CustomerType.BUSINESS;
        };
    }

    /**
     * Converts a domain customer type enum to a persistence customer type enum.
     * 
     * @param type the domain customer type to convert
     * @return the corresponding persistence customer type
     */
     private com.demo.customers.adapter.persistence.entity.CustomerType toEntityCustomerType(com.demo.customers.domain.model.CustomerType type) {
         return switch (type) {
             case INDIVIDUAL -> com.demo.customers.adapter.persistence.entity.CustomerType.INDIVIDUAL;
             case BUSINESS -> com.demo.customers.adapter.persistence.entity.CustomerType.BUSINESS;
         };
    }
}