package com.demo.notification.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class NotificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String eventType;

    @Lob
    private String payload;

    @Column(nullable = false)
    private String notificationStatus;

    @Column(nullable = false)
    private String email;
}
