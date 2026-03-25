package com.demo.customers.adapter.rest.mapper;

import com.demo.customers.adapter.rest.dto.AddressDto;
import com.demo.customers.adapter.rest.dto.CustomerResponse;
import com.demo.customers.application.model.view.CustomerView;
import com.demo.customers.application.model.view.dto.AddressView;

import org.springframework.stereotype.Component;

@Component
public class CustomerResponseMapper {

    public CustomerResponse toResponse(CustomerView customerView) {
        return new CustomerResponse(
                customerView.id(),
                customerView.firstName(),
                customerView.lastName(),
                customerView.companyName(),
                customerView.birthDate(),
                customerView.vatNumber(),
                customerView.email(),
                customerView.phoneNumber(),
                toAddressDto(customerView.address()),
                customerView.type()
        );
    }

    private AddressDto toAddressDto(AddressView addressView) {
        return new AddressDto(
                addressView.street(),
                addressView.city(),
                addressView.postalCode(),
                addressView.region(),
                addressView.countryCode()
        );
    }
}
