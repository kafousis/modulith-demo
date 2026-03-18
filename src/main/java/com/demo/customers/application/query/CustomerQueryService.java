package com.demo.customers.application.query;

import java.util.List;

import com.demo.customers.application.exception.CustomerNotFoundException;
import com.demo.customers.application.port.out.CustomerReadPort;
import com.demo.customers.application.query.view.CustomerView;
import com.demo.customers.application.service.mapper.CustomerViewMapper;

import org.springframework.stereotype.Service;

/**
 * Application service for managing customer READ operations.
 *
 * <p>Handles customer query use cases by providing read-only access to customer data
 * through the application layer. Implements domain-driven design by delegating data
 * retrieval through ports and converting domain models to view objects for presentation.
 *
 * <p>All operations are read-only and do not modify system state.
 */
@Service
public class CustomerQueryService {

    private final CustomerReadPort readPort;
    private final CustomerViewMapper mapper;

    /**
     * Constructs a CustomerQueryService with required dependencies.
     *
     * @param readPort the port for reading customer data from the persistence layer
     * @param mapper   the mapper for converting domain objects to view objects for presentation
     */
    public CustomerQueryService(CustomerReadPort readPort, CustomerViewMapper mapper) {
        this.readPort = readPort;
        this.mapper = mapper;
    }

    /**
     * Retrieves a customer by their unique identifier.
     *
     * <p>Queries the persistence layer for a customer with the given ID, maps the domain
     * model to a view object suitable for presentation, and returns the result.
     *
     * @param id the unique identifier of the customer to retrieve
     * @return the customer view containing all customer information
     * @throws CustomerNotFoundException if no customer exists with the given ID
     */
    public CustomerView getCustomer(Long id) {
        return readPort.findById(id)
                .map(mapper::toView)
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer with ID " + id + " not found.")
        );
    }

    /**
     * Retrieves all customers in the system.
     *
     * <p>Queries the persistence layer for all customers, maps each domain model to
     * view objects suitable for presentation, and returns the complete list.
     *
     * @return a list of customer views containing all customers in the system,
     *         or an empty list if no customers exist
     */
    public List<CustomerView> listCustomers() {
        return readPort.findAll()
                .stream()
                .map(mapper::toView)
                .toList();
    }
}
