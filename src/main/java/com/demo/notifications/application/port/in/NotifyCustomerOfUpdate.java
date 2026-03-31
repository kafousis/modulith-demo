package com.demo.notifications.application.port.in;

public interface NotifyCustomerOfUpdate {

    /**
     * Notifies the customer of their account update.
     *
     * @param fullName the full name of the customer
     * @param email the email address of the customer
     */
    void notifyCustomerUpdated(String fullName, String email);
}
