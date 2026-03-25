package com.demo.customers.application.port.in;

import com.demo.customers.application.model.view.CustomerView;

public interface GetCustomerUseCase {

    /**
    * Retrieves a customer's information by their unique identifier.
    *
    * @param id the unique identifier of the customer
    * @return a view object containing the customer's details
    */
    CustomerView getCustomerById(Long id);
}
