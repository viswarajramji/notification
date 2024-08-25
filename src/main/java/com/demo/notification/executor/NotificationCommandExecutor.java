package com.demo.notification.executor;


import com.demo.notification.api.CommandExecutor;
import com.demo.notification.command.NotificationCommand;
import com.demo.notification.entity.NotificationLog;
import com.demo.notification.repo.NotificationLogRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
public class NotificationCommandExecutor implements CommandExecutor<NotificationCommand, NotificationLog> {

    private final NotificationLogRepository notificationLogRepository;
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public NotificationCommandExecutor(NotificationLogRepository notificationLogRepository, JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.notificationLogRepository = notificationLogRepository;
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public NotificationLog execute(NotificationCommand command) {
        // Create and save the notification log
        NotificationLog log = new NotificationLog();
        log.setUserId(command.getUserId());
        log.setEventType(command.getEventName());
        log.setPayload(command.getPayload());
        log.setNotificationStatus("PENDING");
        log.setEmail(command.getEmailId());
        notificationLogRepository.save(log);

        try {
            // Send the email using the specified Thymeleaf template
           sendEmail(command.getEmailId(), command.getTemplateId(), command.getSubject(), command.getContent());
//
            // Update the notification status
            log.setNotificationStatus("SENT");
            notificationLogRepository.save(log);

        } catch (Exception e) {
            // Handle any exceptions and mark the notification as failed
            log.setNotificationStatus("FAILED");
            log.setPayload(e.getMessage());
            notificationLogRepository.save(log);
        }

        return log;
    }

    private void sendEmail(String to, String templateId, String subject, Map<String, Object> contentMap) throws MessagingException {
        Context context = new Context();
        context.setVariables(contentMap);

        String process = templateEngine.process(templateId, context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(process, true);

        mailSender.send(message);
    }
}
