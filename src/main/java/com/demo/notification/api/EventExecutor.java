package com.demo.notification.api;

public interface EventExecutor<T extends Event> {
    void execute(T event);
}
