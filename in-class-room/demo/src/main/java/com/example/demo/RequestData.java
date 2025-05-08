package com.example.demo;

public class RequestData {
    private String userId;
    private String payload;

    public RequestData() {
    }

    public RequestData(String userId, String payload) {
        this.userId = userId;
        this.payload = payload;
    }

    public String getUserId() {
        return userId;
    }

    public String getPayload() {
        return payload;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
