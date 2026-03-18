package com.demo.customers.application.service.mapper;

import com.demo.customers.application.query.view.CustomerView;
import com.demo.customers.application.query.view.dto.AddressView;
import com.demo.customers.domain.model.Address;
import com.demo.customers.domain.model.Customer;

import org.springframework.stereotype.Component;

/**
 * Mapper class to convert Customer domain model to CustomerView DTO.
 */
@Component
public class CustomerViewMapper {

    /**
     * Converts a Customer domain model to a CustomerView DTO.
     *
     * @param customer the Customer domain model to convert
     * @return the corresponding CustomerView DTO
     */
    public CustomerView toView(Customer customer) {
        return new CustomerView(
                customer.id(),
                customer.firstName(),
                customer.lastName(),
                customer.companyName(),
                customer.birthDate(),
                customer.vatNumber(),
                customer.email(),
                customer.phoneNumber(),
                toAddressView(customer.address()),
                customer.type().name()
        );
    }

    /**
     * Helper method to convert an Address domain model to an AddressView DTO.
     *
     * @param address the Address domain model to convert
     * @return the corresponding AddressView DTO
     */
    private static AddressView toAddressView(Address address) {
        return new AddressView(
                address.street(),
                address.city(),
                address.postalCode(),
                address.region(),
                address.countryCode()
        );
    }
}