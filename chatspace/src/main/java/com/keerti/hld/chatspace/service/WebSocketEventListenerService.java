package com.keerti.hld.chatspace.service;

import com.keerti.hld.chatspace.common.enums.MessageType;
import com.keerti.hld.chatspace.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListenerService {

    private final SimpMessageSendingOperations messageSendingOperations;

    @EventListener
    public void handleWebSocketEventListener(SessionConnectedEvent connectedEvent) {
        log.info("Web socket connection received");
    }

    public void handleWebSocketDisconnectListener(SessionDisconnectEvent sessionDisconnectEvent) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());

        String userName = (String) headerAccessor.getSessionAttributes()
                                                    .get("username");

        if(userName != null) {
            log.info("User disconnected: {}", userName);
            ChatMessage message = new ChatMessage();
            message.setType(MessageType.LEAVE);
            message.setSender(userName);

            messageSendingOperations.convertAndSend("topic/public", message);
        }
    }

}
