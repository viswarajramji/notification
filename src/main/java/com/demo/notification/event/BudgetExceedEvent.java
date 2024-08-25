package com.demo.notification.event;

import com.demo.notification.api.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetExceedEvent implements Event {
    private Long expenseId;            // The ID of the expense
    private Long userId;               // The ID of the user who owns the expense
    private String expenseName;        // The name of the expense
    private String expenseDescription; // A description of the expense
    private String expenseType;   // The type of the expense (e.g., FOOD, TRANSPORTATION, etc.)
    private Double amount;
    private Double actualAmount;          // The amount of the expense (positive or negative)
    private String recordType;
    private Long budgetId;             // The ID of the budget
    private String budgetType;         // The type of the budget
    private Double budgetAmount;       // The total budget amount
    private Double budgetSpent;
    private String emailId;// The total amount spent from the budget
}
