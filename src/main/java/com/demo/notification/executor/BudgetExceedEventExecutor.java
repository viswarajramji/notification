package com.demo.notification.executor;

import com.demo.notification.api.EventExecutor;
import com.demo.notification.command.NotificationCommand;
import com.demo.notification.event.BudgetExceedEvent;
import com.demo.notification.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BudgetExceedEventExecutor implements EventExecutor<BudgetExceedEvent> {

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;


    @Autowired
    public BudgetExceedEventExecutor(@Lazy NotificationService notificationService, ObjectMapper objectMapper) {
        this.notificationService=notificationService;
        this.objectMapper=objectMapper;
    }


    @Override
    public void execute(BudgetExceedEvent event) {
        try {
            String payload = objectMapper.writeValueAsString(event);

            Map<String, Object> contentMap = Map.of(
                    "budgetType",event.getBudgetType(),
                    "expenseDescription",event.getExpenseDescription(),
                    "expenseType", event.getExpenseType(),
                    "expenseName", event.getExpenseName(),
                    "amount", event.getAmount(),
                    "actualAmount", event.getActualAmount(),
                    "budgetAmount", event.getBudgetAmount(),
                    "budgetSpent", event.getBudgetSpent(),
                    "recordType", event.getRecordType()
            );

            NotificationCommand command = new NotificationCommand(
                    "BudgetExceedEvent",
                    payload,
                    contentMap,
                    "budget-exceed-notification",
                    event.getEmailId(),
                    "Budget Exceeded Notification"
            );

            notificationService.executeCommand(command);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


