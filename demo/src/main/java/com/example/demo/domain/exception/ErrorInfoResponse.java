package com.example.demo.domain.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorInfoResponse {

    @JsonProperty("message")
    private String message;
    @JsonProperty("statusCode")
    private int statusCode;
    @JsonProperty("description")
    private String uriRequested;

    public ErrorInfoResponse(String message, int statusCode, String uriRequested) {
        this.message = message;
        this.statusCode = statusCode;
        this.uriRequested = uriRequested;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getUriRequested() {
        return uriRequested;
    }

    public void setUriRequested(String uriRequested) {
        this.uriRequested = uriRequested;
    }
}
