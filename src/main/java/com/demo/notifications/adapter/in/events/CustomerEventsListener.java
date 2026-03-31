package com.demo.notifications.adapter.in.events;

import com.demo.customers.api.events.CustomerCreatedEvent;
import com.demo.customers.api.events.CustomerUpdatedEvent;
import com.demo.notifications.application.service.NotificationService;

import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerEventsListener {

    private final NotificationService notificationService;

    /**
     * Listens for CustomerCreatedEvent and triggers a notification to be sent to the customer.
     *
     * @param event the event containing customer creation details
     */
    @ApplicationModuleListener
    public void customerCreatedEventReceived(CustomerCreatedEvent event) {
        log.info("Customer created event received: {}", event);
        notificationService.notifyCustomerCreated(event.fullName(), event.email());
    }

    /**
     * Listens for CustomerUpdatedEvent and triggers a notification to be sent to the customer.
     *
     * @param event the event containing customer update details
     */
    @ApplicationModuleListener
    public void customerUpdatedEventReceived(CustomerUpdatedEvent event) {
        log.info("Customer updated event received: {}", event);
        notificationService.notifyCustomerUpdated(event.fullName(), event.email());
    }
}
