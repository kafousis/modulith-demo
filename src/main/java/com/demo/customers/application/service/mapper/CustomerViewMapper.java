package com.demo.customers.application.service.mapper;

import com.demo.customers.application.query.view.CustomerView;
import com.demo.customers.application.query.view.dto.AddressView;
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
                new AddressView(
                        customer.address().street(),
                        customer.address().city(),
                        customer.address().postalCode(),
                        customer.address().region(),
                        customer.address().countryCode()
                ),
                customer.type().name()
        );
    }
}