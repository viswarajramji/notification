package com.demo.notification.executor;

import com.demo.notification.event.DeleteUserEvent;
import com.demo.notification.repo.NotificationLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeleteNotificationByUserIdEventExecutorTest {

    @Mock
    private NotificationLogRepository notificationLogRepository;

    @InjectMocks
    private DeleteNotificationByUserIdEventExecutor deleteNotificationByUserIdEventExecutor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_Success() {
        // Arrange
        Long userId = 1L;
        DeleteUserEvent event = new DeleteUserEvent(userId);

        // Act
        deleteNotificationByUserIdEventExecutor.execute(event);

        // Assert
        verify(notificationLogRepository, times(1)).deleteByUserId(userId);
    }
}
