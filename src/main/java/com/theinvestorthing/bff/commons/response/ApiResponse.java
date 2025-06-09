package com.theinvestorthing.bff.commons.response;

import java.time.LocalDateTime;

public class ApiResponse <T> {
    private LocalDateTime timestamp;
    private int status;
    private T object;
    private String traceId;

    public ApiResponse() {
    }

    public ApiResponse(LocalDateTime timestamp, int status, T object, String traceId) {
        this.timestamp = timestamp;
        this.status = status;
        this.object = object;
        this.traceId = traceId;
    }

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

    public String getTraceId(){ return this.traceId; }

    public void setTraceId(String traceId) {this.traceId = traceId;}
}
