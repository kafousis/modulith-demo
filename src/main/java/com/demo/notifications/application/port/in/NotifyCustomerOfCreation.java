package com.demo.notifications.application.port.in;

public interface NotifyCustomerOfCreation {

    /**
     * Notifies the customer of their account creation.
     *
     * @param fullName the full name of the customer
     * @param email the email address of the customer
     */
    void notifyCustomerCreated(String fullName, String email);
}
