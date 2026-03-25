package com.demo.customers.application.port.in;

public interface DeleteCustomerUseCase {

    /**
     * Deletes a customer by their ID.
     *
     * @param id the ID of the customer to delete
     */
    void deleteCustomerById(Long id);
}
