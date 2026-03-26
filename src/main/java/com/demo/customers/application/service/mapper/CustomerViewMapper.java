package com.demo.customers.application.service.mapper;

import com.demo.customers.application.model.view.CustomerView;
import com.demo.customers.application.model.view.dto.AddressView;
import com.demo.customers.domain.model.Address;
import com.demo.customers.domain.model.Customer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper component that converts views and DTOs to domain models.
 */
@Mapper(componentModel = "spring")
public interface CustomerViewMapper {

    /**
     * Converts a Customer domain model to a CustomerView DTO.
     *
     * @param customer the Customer domain model to convert
     * @return the corresponding CustomerView DTO
     */
    @Mapping(target = "type", expression = "java(customer.type().name())")
    CustomerView toView(Customer customer);

    /**
     * Converts Address domain model to AddressView DTO.
     *
     * @param address the Address domain model to convert
     * @return the corresponding AddressView DTO
     */
    AddressView toAddressView(Address address);
}