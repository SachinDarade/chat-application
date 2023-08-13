package com.keerti.hld.chatspace.common.dal.dao;

import com.keerti.hld.chatspace.common.enums.MessageType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document("chat_messages")
public class ChatMessage {

    @Id
    private String messageId;

    private String senderName;
    private String receiverName;
    private String message;
    private String date;
    private MessageType status;

}
