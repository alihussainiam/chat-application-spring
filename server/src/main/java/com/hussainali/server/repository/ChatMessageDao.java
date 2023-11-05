package com.hussainali.server.repository;

import com.hussainali.server.chat.ChatMessage;

import java.util.List;

public interface ChatMessageDao {
    boolean saveMessageToGroup(ChatMessage chatMessage);

    List<Object> fetchAllMessage();

    boolean deleteMessageGroup();

}
