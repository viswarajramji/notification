package com.demo.notification.executor;

import com.demo.notification.command.FetchAllNotificationLogsByUserIdCommand;
import com.demo.notification.entity.NotificationLog;
import com.demo.notification.repo.NotificationLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class FetchAllNotificationLogsByUserIdCommandExecutorTest {

    @Mock
    private NotificationLogRepository notificationLogRepository;

    @InjectMocks
    private FetchAllNotificationLogsByUserIdCommandExecutor fetchAllNotificationLogsByUserIdCommandExecutor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_Success() {
        // Arrange
        Long userId = 1L;
        FetchAllNotificationLogsByUserIdCommand command = new FetchAllNotificationLogsByUserIdCommand(userId);
        List<NotificationLog> logs = Arrays.asList(new NotificationLog(), new NotificationLog());

        when(notificationLogRepository.findByUserId(userId)).thenReturn(logs);

        // Act
        List<NotificationLog> result = fetchAllNotificationLogsByUserIdCommandExecutor.execute(command);

        // Assert
        assertNotNull(result);
        assertEquals(logs.size(), result.size());
        assertEquals(logs, result);
    }
}

