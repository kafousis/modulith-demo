package com.demo.notifications.adapter.out.email;

import com.demo.notifications.application.port.out.NotificationDeliveryPort;
import com.demo.notifications.domain.model.Notification;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmailNotificationDeliveryAdapter implements NotificationDeliveryPort {

    /**
     * In a real implementation, you would inject an email service (e.g. JavaMailSender)
     * to send the email. For this example, we will simply log the notification details.
     */
    @Override
    public void send(Notification notification) {
        // Simulate sending an email by logging the notification details
        log.info("Sending email to: {}", notification.recipientEmail());
        log.info("Subject: {}", notification.subject());
        log.info("Message: {}", notification.message());
    }
}
