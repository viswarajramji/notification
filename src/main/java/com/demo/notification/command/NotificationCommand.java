package com.demo.notification.command;


import com.demo.notification.api.Command;
import com.demo.notification.api.CommandExecutor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationCommand implements Command {
    private String eventName;
    private String payload;
    private Map<String, Object> content;
    private String templateId;
    private String emailId;
    private String subject;
}
