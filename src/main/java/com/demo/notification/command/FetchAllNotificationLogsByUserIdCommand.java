package com.demo.notification.command;

import com.demo.notification.api.Command;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FetchAllNotificationLogsByUserIdCommand implements Command {
    @NotNull
    public Long userId;
}
