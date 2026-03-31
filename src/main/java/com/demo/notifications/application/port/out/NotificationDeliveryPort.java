package com.demo.notifications.application.port.out;

import com.demo.notifications.domain.model.Notification;

public interface NotificationDeliveryPort {

    /**
     * Sends a notification based on the provided Notification object.
     *
     * @param notification the notification details to be sent via email
     */
    void send(Notification notification);
}
