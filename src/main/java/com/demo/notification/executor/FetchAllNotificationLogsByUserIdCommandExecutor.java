package com.demo.notification.executor;

import com.demo.notification.api.CommandExecutor;
import com.demo.notification.command.FetchAllNotificationLogsByUserIdCommand;
import com.demo.notification.command.FetchAllNotificationLogsCommand;
import com.demo.notification.entity.NotificationLog;
import com.demo.notification.repo.NotificationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchAllNotificationLogsByUserIdCommandExecutor implements CommandExecutor<FetchAllNotificationLogsByUserIdCommand, List<NotificationLog>> {

    @Autowired
    private NotificationLogRepository notificationLogRepository;

    @Override
    public List<NotificationLog> execute(FetchAllNotificationLogsByUserIdCommand command) {
        return notificationLogRepository.findByUserId(command.getUserId());
    }
}