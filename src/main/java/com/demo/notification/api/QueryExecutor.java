package com.demo.notification.api;

public interface QueryExecutor<T extends Query, R> {
    R execute(T query);
}

