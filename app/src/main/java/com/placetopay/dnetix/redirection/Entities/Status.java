package com.placetopay.dnetix.redirection.Entities;

import com.placetopay.dnetix.redirection.Contracts.Mappable;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Status implements Mappable {

    private String status;
    private String message;
    private String reason;
    private String date;

    public Status() {
        this.status = "FAILED";
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getReason() {
        return reason;
    }

    public String getDate() {
        return date;
    }

    public Status setStatus(String status) {
        this.status = status;
        return this;
    }

    public Status setMessage(String message) {
        this.message = message;
        return this;
    }

    public Status setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public Status setDate(String date) {
        this.date = date;
        return this;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", getStatus());
        map.put("message", getMessage());
        map.put("reason", getReason());
        map.put("date", getDate());
        return map;
    }
}
