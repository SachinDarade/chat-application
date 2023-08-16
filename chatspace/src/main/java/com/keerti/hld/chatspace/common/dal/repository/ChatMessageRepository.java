package com.keerti.hld.chatspace.common.dal.repository;

import com.keerti.hld.chatspace.common.dal.dao.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    List<ChatMessage> findBySenderName(String senderName);

}
