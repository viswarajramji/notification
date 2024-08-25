package com.demo.notification.executor;
import com.demo.notification.command.NotificationCommand;
import com.demo.notification.event.BudgetExceedEvent;
import com.demo.notification.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BudgetExceedEventExecutorTest {

    @Mock
    private NotificationService notificationService;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private BudgetExceedEventExecutor budgetExceedEventExecutor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_Success() throws Exception {
        // Arrange
        BudgetExceedEvent event = new BudgetExceedEvent(
                1L, 2L, "Groceries", "Monthly groceries", "FOOD",
                200.0, 250.0, "EXPENSE", 1L, "Monthly Budget",
                1000.0, 950.0, "user@example.com"
        );

        String payload = "{\"userId\":2,\"expenseName\":\"Groceries\",\"expenseDescription\":\"Monthly groceries\"}"; // simplified
        when(objectMapper.writeValueAsString(event)).thenReturn(payload);

        // Act
        budgetExceedEventExecutor.execute(event);

        // Assert
        verify(objectMapper, times(1)).writeValueAsString(event);
        verify(notificationService, times(1)).executeCommand(any(NotificationCommand.class));
    }

    @Test
    void testExecute_ExceptionHandling() throws Exception {
        // Arrange
        BudgetExceedEvent event = new BudgetExceedEvent(
                1L, 2L, "Groceries", "Monthly groceries", "FOOD",
                200.0, 250.0, "EXPENSE", 1L, "Monthly Budget",
                1000.0, 950.0, "user@example.com"
        );

        when(objectMapper.writeValueAsString(event)).thenThrow(new RuntimeException("Serialization error"));

        // Act
        budgetExceedEventExecutor.execute(event);

        // Assert
        verify(objectMapper, times(1)).writeValueAsString(event);
        verify(notificationService, never()).executeCommand(any(NotificationCommand.class));
    }
}
