package com.wowoduble.springbootdemo.websocket;

public class WowResponse {

    private String responseMessage ;

    public WowResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
