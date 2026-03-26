package com.demo.customers.adapter.persistence.mapper;

import com.demo.customers.adapter.persistence.entity.CustomerEntity;
import com.demo.customers.domain.model.Customer;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

    Customer toDomain(CustomerEntity entity);

    CustomerEntity toEntity(Customer customer);

    com.demo.customers.adapter.persistence.entity.Address toEntityAddress(com.demo.customers.domain.model.Address address);

    com.demo.customers.domain.model.Address toDomainAddress(com.demo.customers.adapter.persistence.entity.Address address);

    com.demo.customers.domain.model.CustomerType toDomainCustomerType(com.demo.customers.adapter.persistence.entity.CustomerType type);

    com.demo.customers.adapter.persistence.entity.CustomerType toEntityCustomerType(com.demo.customers.domain.model.CustomerType type);
}