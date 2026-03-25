package com.demo.customers.adapter.rest.mapper;

import com.demo.customers.adapter.rest.dto.AddressDto;
import com.demo.customers.adapter.rest.dto.CustomerRequest;
import com.demo.customers.application.model.command.CustomerCommand;
import com.demo.customers.application.model.command.input.AddressInput;
import com.demo.customers.application.model.command.input.CustomerTypeInput;

import org.springframework.stereotype.Component;

/**
 * Mapper class responsible for converting CustomerRequest DTOs from the API layer into CustomerCommand objects
 * that can be processed by the application layer. This class handles the mapping of customer data, including
 * nested address information and customer type.
 */
@Component
public class CustomerRequestMapper {

    /**
     * Maps a CustomerRequest DTO to a CustomerCommand, which is used in the application layer.
     *
     * @param request The CustomerRequest DTO containing the customer data from the API request.
     * @return A CustomerCommand object that can be processed by the application layer.
     */
    public CustomerCommand toCommand(CustomerRequest request) {
        return new CustomerCommand(
                request.firstName(),
                request.lastName(),
                request.companyName(),
                request.birthDate(),
                request.vatNumber(),
                request.email(),
                request.phoneNumber(),
                toAddressInput(request.address()),
                toTypeInput(request.type())
        );
    }

    /**
     * Maps an AddressDto to an AddressInput, which is used in the application layer.
     *
     * @param request The AddressDto containing the address data from the API request.
     * @return An AddressInput object that can be processed by the application layer.
     */
    private AddressInput toAddressInput(AddressDto request) {
        return new AddressInput(
                request.street(),
                request.city(),
                request.postalCode(),
                request.region(),
                request.countryCode()
        );
    }

    /**
     * Maps a string representation of a customer type to a CustomerTypeInput enum.
     *
     * @param type The string representation of the customer type (e.g., "individual", "company").
     * @return A CustomerTypeInput enum value corresponding to the provided string.
     */
    private CustomerTypeInput toTypeInput(String type) {
        return CustomerTypeInput.valueOf(type.toUpperCase());
    }
}