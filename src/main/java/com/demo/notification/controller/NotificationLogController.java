package com.demo.notification.controller;

import com.demo.notification.command.FetchAllNotificationLogsByUserIdCommand;
import com.demo.notification.command.FetchAllNotificationLogsCommand;
import com.demo.notification.entity.NotificationLog;
import com.demo.notification.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications/query")
public class NotificationLogController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/all")
    public ResponseEntity<List<NotificationLog>> getAllNotificationLogs() {
        // Execute the command to fetch all notification logs
        FetchAllNotificationLogsCommand command = new FetchAllNotificationLogsCommand();
        List<NotificationLog> logs = notificationService.executeCommand(command);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<NotificationLog>> getNotificationLogsByUserId(@Valid @PathVariable Long userId) {
        // Execute the command to fetch notification logs by userId
        FetchAllNotificationLogsByUserIdCommand command = new FetchAllNotificationLogsByUserIdCommand(userId);
        List<NotificationLog> logs = notificationService.executeCommand(command);
        return ResponseEntity.ok(logs);
    }


}
