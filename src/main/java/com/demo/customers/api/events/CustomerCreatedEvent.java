package com.demo.customers.api.events;

public record CustomerCreatedEvent(String fullName, String email) { }