package com.demo.notification.repo;


import com.demo.notification.entity.NotificationLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationLogRepository extends JpaRepository<NotificationLog, Long> {
    void deleteByUserId(Long userId);
    List<NotificationLog> findByUserId(Long userId);
}
