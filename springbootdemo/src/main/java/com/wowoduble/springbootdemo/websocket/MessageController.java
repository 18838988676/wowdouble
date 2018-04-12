package com.wowoduble.springbootdemo.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MessageController {

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public WowResponse sendMessage(WowMessage message) throws InterruptedException {
        Thread.sleep(3000);
        return new WowResponse("welcome "+message+" !");
    }

    @RequestMapping("/websocket")
    public String  tows(){
        return "ws";
    }
}
