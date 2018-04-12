package com.example.websocket;

public class MessageRequest {

    private String message ;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageRequest(String message) {
        this.message = message;
    }
}
