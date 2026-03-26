package com.demo.customers.adapter.in.rest.mapper;

import com.demo.customers.adapter.in.rest.dto.AddressDto;
import com.demo.customers.adapter.in.rest.dto.CustomerRequest;
import com.demo.customers.application.model.command.CustomerCommand;
import com.demo.customers.application.model.command.input.AddressInput;
import com.demo.customers.application.model.command.input.CustomerTypeInput;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper responsible for converting CustomerRequest DTOs from the API layer into CustomerCommand objects
 * that can be processed by the application layer. This class handles the mapping of customer data, including
 * nested address information and customer type.
 */
@Mapper(componentModel = "spring")
public interface CustomerRequestMapper {

    /**
     * Maps a CustomerRequest DTO to a CustomerCommand, which is used in the application layer.
     *
     * @param request The CustomerRequest DTO containing the customer data from the API request.
     * @return A CustomerCommand object that can be processed by the application layer.
     */
    @Mapping(target = "type", source = "type", qualifiedByName = "toCustomerTypeInput")
    CustomerCommand toCommand(CustomerRequest request);

    /**
     * Maps an AddressDto to an AddressInput, which is used in the application layer.
     *
     * @param request The AddressDto containing the address data from the API request.
     * @return An AddressInput object that can be processed by the application layer.
     */
    AddressInput toAddressInput(AddressDto request);

    /**
     * Maps a string representation of a customer type to a CustomerTypeInput enum.
     *
     * @param type The string representation of the customer type (e.g., "individual", "company").
     * @return A CustomerTypeInput enum value corresponding to the provided string.
     */
    @Named("toCustomerTypeInput")
    default CustomerTypeInput toCustomerTypeInput(String type) {
        try {
            return CustomerTypeInput.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid customer type");
        }
    }
}