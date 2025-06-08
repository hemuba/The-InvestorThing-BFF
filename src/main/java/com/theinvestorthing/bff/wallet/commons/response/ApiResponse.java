package com.theinvestorthing.bff.wallet.commons.response;

import java.time.LocalDateTime;

public class ApiResponse <T> {
    private LocalDateTime timestamp;
    private int status;
    private T object;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public ApiResponse() {
    }

    public ApiResponse(LocalDateTime timestamp, int status, T object) {
        this.timestamp = timestamp;
        this.status = status;
        this.object = object;
    }
}
