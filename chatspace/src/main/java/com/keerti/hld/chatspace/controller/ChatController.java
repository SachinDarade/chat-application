package com.keerti.hld.chatspace.controller;

import com.keerti.hld.chatspace.model.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ChatController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/sendAll")
//    @SendTo("/topic/public")
    public ChatMessage sendMessageToAll(@Payload ChatMessage message) {
        log.info("Sending information to everyone: {}", message.toString());
        messagingTemplate.convertAndSend("/topic/public", message);
        return message;
    }

    @MessageMapping("/chat/user")
//    @SendTo("/topic/public")
    public ChatMessage sendMessageToUser(@Payload ChatMessage message) {
        log.info("Sending information to {}", message.getReceiver());
        messagingTemplate.convertAndSend("/topic/user/" + message.getReceiver(), message);
        messagingTemplate.convertAndSend("/topic/user/" + message.getSender(), message);
        return message;
    }

    @MessageMapping("/addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(
            @Payload ChatMessage message,
            SimpMessageHeaderAccessor messageHeaderAccessor
    ) {
        log.info("Adding user: {}", message.getSender());
        messageHeaderAccessor.getSessionAttributes()
                .put("username", message.getSender());
        return message;
    }


}
