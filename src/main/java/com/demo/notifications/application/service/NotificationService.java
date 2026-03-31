package com.demo.notifications.application.service;

import com.demo.notifications.application.port.in.NotifyCustomerOfCreation;
import com.demo.notifications.application.port.in.NotifyCustomerOfUpdate;
import com.demo.notifications.application.port.out.NotificationDeliveryPort;
import com.demo.notifications.domain.model.Notification;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService implements NotifyCustomerOfCreation, NotifyCustomerOfUpdate {

    private final NotificationDeliveryPort notificationDeliveryPort;

    /**
     * Notifies the customer of their account creation by sending a welcome message to their email.
     *
     * @param fullName the full name of the customer
     * @param email the email address of the customer
     */
    @Override
    public void notifyCustomerCreated(String fullName, String email) {
        String subject = "Welcome to Our Service!";
        String message = String.format("Welcome %s! Your account has been successfully created.", fullName);
        notificationDeliveryPort.send(new Notification(fullName, email, subject, message));
    }

    /**
     * Notifies the customer of their account update by sending a message to their email.
     *
     * @param fullName the full name of the customer
     * @param email the email address of the customer
     */
    @Override
    public void notifyCustomerUpdated(String fullName, String email) {
        String subject = "Your Account Information Has Been Updated";
        String message = String.format("Hello %s! Your account information has been updated.", fullName);
        notificationDeliveryPort.send(new Notification(fullName, email, subject, message));
    }
}
