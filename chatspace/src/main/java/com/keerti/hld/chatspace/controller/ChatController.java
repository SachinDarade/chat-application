package com.keerti.hld.chatspace.controller;

import com.keerti.hld.chatspace.common.dal.dao.ChatMessage;
import com.keerti.hld.chatspace.common.dal.repository.ChatMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

//    @MessageMapping("/chat/sendAll")
//    @SendTo("/topic/public")
//    public ChatMessage sendMessage(@Payload ChatMessage message) {
//        log.info("Sending information to everyone");
//        return message;
//    }
//
//    @MessageMapping("/addUser")
//    @SendTo("/topic/public")
//    public ChatMessage addUser(
//            @Payload ChatMessage message,
//            SimpMessageHeaderAccessor messageHeaderAccessor
//    ) {
//        log.info("Adding user: {}", message.getSender());
//        messageHeaderAccessor.getSessionAttributes()
//                .put("username", message.getSender());
//        return message;
//    }


    ///////////////////////// One to One chat ////////////////////////
    @MessageMapping("/message")     // Will listen to all messages coming to app/message
    @SendTo("/chatroom/public")
    public ChatMessage receivePublicMessage(@Payload ChatMessage message) {
        message.setReceiverName("EVERYONE");
        chatMessageRepository.save(message);
        return message;
    }

    @MessageMapping("/private-message")
    public ChatMessage receivePrivateMessage(@Payload ChatMessage message) {
        chatMessageRepository.save(message);
        messagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);  // Sends message to client listening on /user/sachin/private
        return message;
    }


}
