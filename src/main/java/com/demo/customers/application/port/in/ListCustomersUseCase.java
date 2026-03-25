package com.demo.customers.application.port.in;

import java.util.List;

import com.demo.customers.application.model.view.CustomerView;

public interface ListCustomersUseCase {

    /**
     * Retrieves a list of all customers in the system.
     *
     * @return a list of customer view objects
     */
    List<CustomerView> listCustomers();
}
