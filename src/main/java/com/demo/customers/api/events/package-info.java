/*
 * This package contains the events that are published by the customers' module.
 * These events are used to notify other modules about changes in the customers module.
 * The events are published using Spring's ApplicationEventPublisher.
 * The events are consumed by the notifications module to send notifications to the customers.
 */
@NamedInterface("customer-events")
package com.demo.customers.api.events;

import org.springframework.modulith.NamedInterface;