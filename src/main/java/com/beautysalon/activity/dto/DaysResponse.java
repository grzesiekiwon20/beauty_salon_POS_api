package com.beautysalon.activity.dto;

public class DaysResponse {
    private boolean success;
    private String message;
    private Long days;

    public DaysResponse(boolean success, String message, Long days) {
        this.success = success;
        this.message = message;
        this.days = days;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }
}
