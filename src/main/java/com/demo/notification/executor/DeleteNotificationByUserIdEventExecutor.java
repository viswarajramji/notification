package com.demo.notification.executor;


import com.demo.notification.api.EventExecutor;
import com.demo.notification.event.DeleteUserEvent;
import com.demo.notification.repo.NotificationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteNotificationByUserIdEventExecutor implements EventExecutor<DeleteUserEvent> {

    private final NotificationLogRepository notificationLogRepository;

    @Autowired
    public DeleteNotificationByUserIdEventExecutor(NotificationLogRepository notificationLogRepository) {
        this.notificationLogRepository = notificationLogRepository;
    }

    @Override
    public void execute(DeleteUserEvent command) {
        notificationLogRepository.deleteByUserId(command.getUserId());
    }
}
