package com.demo.notification.executor;

import com.demo.notification.command.NotificationCommand;
import com.demo.notification.entity.NotificationLog;
import com.demo.notification.repo.NotificationLogRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.NestedRuntimeException;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class NotificationCommandExecutorTest {

    @Mock
    private NotificationLogRepository notificationLogRepository;

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private TemplateEngine templateEngine;

    @InjectMocks
    private NotificationCommandExecutor notificationCommandExecutor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_Success() throws Exception {
        // Arrange
        NotificationCommand command = new NotificationCommand(
                1L, "BudgetExceedEvent", "Payload",
                new HashMap<>(), "templateId", "user@example.com", "Subject"
        );

        NotificationLog log = new NotificationLog();
        log.setUserId(command.getUserId());
        log.setEventType(command.getEventName());
        log.setPayload(command.getPayload());
        log.setNotificationStatus("PENDING");
        log.setEmail(command.getEmailId());

        when(notificationLogRepository.save(any(NotificationLog.class))).thenReturn(log);

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(templateEngine.process(eq(command.getTemplateId()), any(Context.class))).thenReturn("Processed Content");

        // Act
        NotificationLog result = notificationCommandExecutor.execute(command);

        // Assert
        assertNotNull(result);
        assertEquals("SENT", result.getNotificationStatus());
        verify(notificationLogRepository, times(2)).save(any(NotificationLog.class));
        verify(mailSender, times(1)).send(mimeMessage);
    }

    @Test
    void testExecute_EmailSendingFailure() throws Exception {
        // Arrange
        NotificationCommand command = new NotificationCommand(
                1L, "BudgetExceedEvent", "Payload",
                new HashMap<>(), "templateId", "user@example.com", "Subject"
        );

        NotificationLog log = new NotificationLog();
        log.setUserId(command.getUserId());
        log.setEventType(command.getEventName());
        log.setPayload(command.getPayload());
        log.setNotificationStatus("PENDING");
        log.setEmail(command.getEmailId());

        when(notificationLogRepository.save(any(NotificationLog.class))).thenReturn(log);

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(templateEngine.process(eq(command.getTemplateId()), any(Context.class))).thenReturn("Processed Content");

        doThrow(new RuntimeException("Failed to send email")).when(mailSender).send(mimeMessage);

        // Act
        NotificationLog result = notificationCommandExecutor.execute(command);

        // Assert
        assertNotNull(result);
        assertEquals("FAILED", result.getNotificationStatus());
        assertEquals("Failed to send email", result.getPayload());
        verify(notificationLogRepository, times(2)).save(any(NotificationLog.class));
        verify(mailSender, times(1)).send(mimeMessage);
    }
}
