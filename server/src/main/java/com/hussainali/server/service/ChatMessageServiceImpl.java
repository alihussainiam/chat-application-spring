package com.hussainali.server.service;

import com.hussainali.server.chat.ChatMessage;
import com.hussainali.server.repository.ChatMessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChatMessageServiceImpl implements ChatMessageService{

    @Autowired
    private ChatMessageDao chatMessageDao;


    @Override
    public boolean saveMessage(ChatMessage chatMessage) {
        return chatMessageDao.saveMessageToGroup(chatMessage);
    }
    @Override
    public List<Object> fetchAllMessages(){
        return chatMessageDao.fetchAllMessage();
    }
    @Override
    public boolean deleteChatRoom(){
        return chatMessageDao.deleteMessageGroup();
    }

}
