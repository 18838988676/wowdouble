package com.example.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import sun.java2d.pipe.SpanShapeRenderer;

import java.security.Principal;

@Controller
public class MessageController {

    @MessageMapping("/testMessage")
    @SendTo("/topic/testTopic")
    public MessageResponse response(String name){
        System.out.println("name=="+name);
        return new MessageResponse(name);
    }

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate ;
    @MessageMapping("/chat")
    public void handlerChat(Principal principal ,String msg){
        if(principal.getName().equals("wyf")){
            simpMessagingTemplate.convertAndSendToUser("wisely","/quene/notifications",principal.getName()+"-send:"+msg);
        }else    if(principal.getName().equals("wisely")){
            simpMessagingTemplate.convertAndSendToUser("wyf","/quene/notifications",principal.getName()+"-send:"+msg);
        }
    }
}
