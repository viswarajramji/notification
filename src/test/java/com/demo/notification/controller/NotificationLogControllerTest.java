package com.demo.notification.controller;
import com.demo.notification.command.FetchAllNotificationLogsByUserIdCommand;
import com.demo.notification.command.FetchAllNotificationLogsCommand;
import com.demo.notification.entity.NotificationLog;
import com.demo.notification.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class NotificationLogControllerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationLogController notificationLogController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllNotificationLogs() {
        // Arrange
        List<NotificationLog> logs = Arrays.asList(new NotificationLog(), new NotificationLog());
        when(notificationService.executeCommand(any(FetchAllNotificationLogsCommand.class))).thenReturn(logs);

        // Act
        ResponseEntity<List<NotificationLog>> response = notificationLogController.getAllNotificationLogs();

        // Assert
        assertNotNull(response);
        assertEquals(logs, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(notificationService).executeCommand(any(FetchAllNotificationLogsCommand.class));
    }

    @Test
    void testGetNotificationLogsByUserId() {
        // Arrange
        Long userId = 1L;
        List<NotificationLog> logs = Arrays.asList(new NotificationLog(), new NotificationLog());
        when(notificationService.executeCommand(any(FetchAllNotificationLogsByUserIdCommand.class))).thenReturn(logs);

        // Act
        ResponseEntity<List<NotificationLog>> response = notificationLogController.getNotificationLogsByUserId(userId);

        // Assert
        assertNotNull(response);
        assertEquals(logs, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(notificationService).executeCommand(any(FetchAllNotificationLogsByUserIdCommand.class));
    }
}

