package com.demo.notifications.domain.model;

/**
 * Represents a notification to be sent to a recipient, containing the recipient's name, email, subject, and message.
 *
 * @param recipientName the full name of the recipient
 * @param recipientEmail the email address of the recipient
 * @param subject the subject of the notification
 * @param message the body of the notification message
 */
public record Notification(String recipientName, String recipientEmail, String subject, String message) { }
