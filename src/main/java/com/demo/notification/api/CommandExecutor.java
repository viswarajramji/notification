package com.demo.notification.api;

public interface CommandExecutor<T extends Command, R> {
    R execute(T command);
}

