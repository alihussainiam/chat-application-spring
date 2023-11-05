package com.hussainali.server.service;

import com.hussainali.server.chat.ChatMessage;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ChatMessageService {
    boolean saveMessage(ChatMessage chatMessage);

    List<Object> fetchAllMessages();

    boolean deleteChatRoom();

}