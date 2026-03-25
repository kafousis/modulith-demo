package com.demo.customers.adapter.rest.mapper;

import com.demo.customers.adapter.rest.dto.AddressDto;
import com.demo.customers.adapter.rest.dto.CustomerResponse;
import com.demo.customers.application.model.view.CustomerView;
import com.demo.customers.application.model.view.dto.AddressView;

import org.springframework.stereotype.Component;

/**
 * Mapper class responsible for converting CustomerView objects from the application layer into CustomerResponse DTOs
 * that can be returned in the API responses. This class handles the mapping of customer data, including nested
 * address information and customer type.
 */
@Component
public class CustomerResponseMapper {

    /**
     * Maps a CustomerView from the application layer to a CustomerResponse DTO for the API layer.
     *
     * @param customerView The CustomerView containing the customer data from the application layer.
     * @return A CustomerResponse DTO that can be returned in the API response.
     */
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

    /**
     * Maps an AddressView from the application layer to an AddressDto for the API layer.
     *
     * @param addressView The AddressView containing the address data from the application layer.
     * @return An AddressDto that can be included in the API response.
     */
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
