package com.demo.notification.event;
import com.demo.notification.api.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteUserEvent implements Event {
    private Long userId;
}