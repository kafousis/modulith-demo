package com.demo.customers.adapter.rest.mapper;

import com.demo.customers.adapter.rest.dto.AddressDto;
import com.demo.customers.adapter.rest.dto.CustomerResponse;
import com.demo.customers.application.model.view.CustomerView;
import com.demo.customers.application.model.view.dto.AddressView;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerResponseMapper {

    CustomerResponse toResponse(CustomerView customerView);

    AddressDto toAddressDto(AddressView addressView);
}
