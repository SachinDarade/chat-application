package com.keerti.hld.chatspace.model;

import com.keerti.hld.chatspace.common.enums.MessageType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatMessage {

    private String senderName;
    private String receiverName;
    private String message;
    private String date;
    private MessageType status;

}
