package com.demo.notification.command;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NotificationCommandTest {

    @Test
    void testNotificationCommandCreation() {
        // Arrange
        Long userId = 1L;
        String eventName = "UserRegistered";
        String payload = "Sample payload";
        Map<String, Object> content = new HashMap<>();
        content.put("key1", "value1");
        String templateId = "template123";
        String emailId = "user@example.com";
        String subject = "Welcome to our service";

        // Act
        NotificationCommand command = new NotificationCommand(userId, eventName, payload, content, templateId, emailId, subject);

        // Assert
        assertNotNull(command);
        assertEquals(userId, command.getUserId());
        assertEquals(eventName, command.getEventName());
        assertEquals(payload, command.getPayload());
        assertEquals(content, command.getContent());
        assertEquals(templateId, command.getTemplateId());
        assertEquals(emailId, command.getEmailId());
        assertEquals(subject, command.getSubject());
    }

    @Test
    void testNotificationCommandDefaultConstructor() {
        // Act
        NotificationCommand command = new NotificationCommand();

        // Assert
        assertNotNull(command);
        assertEquals(null, command.getUserId());
        assertEquals(null, command.getEventName());
        assertEquals(null, command.getPayload());
        assertEquals(null, command.getContent());
        assertEquals(null, command.getTemplateId());
        assertEquals(null, command.getEmailId());
        assertEquals(null, command.getSubject());
    }
}
