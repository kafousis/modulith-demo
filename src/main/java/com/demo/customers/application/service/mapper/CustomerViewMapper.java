package com.demo.customers.application.service.mapper;

import com.demo.customers.application.model.view.CustomerView;
import com.demo.customers.application.model.view.dto.AddressView;
import com.demo.customers.domain.model.Address;
import com.demo.customers.domain.model.Customer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerViewMapper {

    @Mapping(target = "type", expression = "java(customer.type().name())")
    CustomerView toView(Customer customer);

    AddressView toAddressView(Address address);
}