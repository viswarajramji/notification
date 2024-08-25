package com.demo.notification.executor;

import com.demo.notification.command.FetchAllNotificationLogsCommand;
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

class FetchAllNotificationLogsCommandExecutorTest {

    @Mock
    private NotificationLogRepository notificationLogRepository;

    @InjectMocks
    private FetchAllNotificationLogsCommandExecutor fetchAllNotificationLogsCommandExecutor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_Success() {
        // Arrange
        FetchAllNotificationLogsCommand command = new FetchAllNotificationLogsCommand();
        List<NotificationLog> logs = Arrays.asList(new NotificationLog(), new NotificationLog());

        when(notificationLogRepository.findAll()).thenReturn(logs);

        // Act
        List<NotificationLog> result = fetchAllNotificationLogsCommandExecutor.execute(command);

        // Assert
        assertNotNull(result);
        assertEquals(logs.size(), result.size());
        assertEquals(logs, result);
    }
}
