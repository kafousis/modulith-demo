package com.demo.customers.api.events;

public record CustomerUpdatedEvent(String fullName, String email) { }