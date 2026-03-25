package com.demo.customers.application.model.command;

import java.time.LocalDate;

import com.demo.customers.application.model.command.input.AddressInput;
import com.demo.customers.application.model.command.input.CustomerTypeInput;

public record CustomerCommand(
        String firstName,
        String lastName,
        String companyName,
        LocalDate birthDate,
        String vatNumber,
        String email,
        String phoneNumber,
        AddressInput address,
        CustomerTypeInput type
) {}
