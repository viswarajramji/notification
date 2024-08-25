package com.demo.notification.event;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BudgetExceedEventTest {

    @Test
    void testBudgetExceedEventCreation() {
        // Arrange
        Long expenseId = 1L;
        Long userId = 2L;
        String expenseName = "Grocery Shopping";
        String expenseDescription = "Weekly grocery shopping";
        String expenseType = "FOOD";
        Double amount = 200.0;
        Double actualAmount = 220.0;
        String recordType = "Expense";
        Long budgetId = 1L;
        String budgetType = "Monthly Budget";
        Double budgetAmount = 1000.0;
        Double budgetSpent = 900.0;
        String emailId = "user@example.com";

        // Act
        BudgetExceedEvent event = new BudgetExceedEvent(expenseId, userId, expenseName, expenseDescription, expenseType, amount, actualAmount, recordType, budgetId, budgetType, budgetAmount, budgetSpent, emailId);

        // Assert
        assertNotNull(event);
        assertEquals(expenseId, event.getExpenseId());
        assertEquals(userId, event.getUserId());
        assertEquals(expenseName, event.getExpenseName());
        assertEquals(expenseDescription, event.getExpenseDescription());
        assertEquals(expenseType, event.getExpenseType());
        assertEquals(amount, event.getAmount());
        assertEquals(actualAmount, event.getActualAmount());
        assertEquals(recordType, event.getRecordType());
        assertEquals(budgetId, event.getBudgetId());
        assertEquals(budgetType, event.getBudgetType());
        assertEquals(budgetAmount, event.getBudgetAmount());
        assertEquals(budgetSpent, event.getBudgetSpent());
        assertEquals(emailId, event.getEmailId());
    }

    @Test
    void testBudgetExceedEventDefaultConstructor() {
        // Act
        BudgetExceedEvent event = new BudgetExceedEvent();

        // Assert
        assertNotNull(event);
        assertEquals(null, event.getExpenseId());
        assertEquals(null, event.getUserId());
        assertEquals(null, event.getExpenseName());
        assertEquals(null, event.getExpenseDescription());
        assertEquals(null, event.getExpenseType());
        assertEquals(null, event.getAmount());
        assertEquals(null, event.getActualAmount());
        assertEquals(null, event.getRecordType());
        assertEquals(null, event.getBudgetId());
        assertEquals(null, event.getBudgetType());
        assertEquals(null, event.getBudgetAmount());
        assertEquals(null, event.getBudgetSpent());
        assertEquals(null, event.getEmailId());
    }
}
