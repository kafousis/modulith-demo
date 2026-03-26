package com.demo.customers.adapter.rest.mapper;

import com.demo.customers.adapter.rest.dto.AddressDto;
import com.demo.customers.adapter.rest.dto.CustomerRequest;
import com.demo.customers.application.model.command.CustomerCommand;
import com.demo.customers.application.model.command.input.AddressInput;
import com.demo.customers.application.model.command.input.CustomerTypeInput;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CustomerRequestMapper {

    @Mapping(target = "type", source = "type", qualifiedByName = "toCustomerTypeInput")
    CustomerCommand toCommand(CustomerRequest request);

    AddressInput toAddressInput(AddressDto request);

    @Named("toCustomerTypeInput")
    default CustomerTypeInput toCustomerTypeInput(String type) {
        try {
            return CustomerTypeInput.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid customer type");
        }
    }
}