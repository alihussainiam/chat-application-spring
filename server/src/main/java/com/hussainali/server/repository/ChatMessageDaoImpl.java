package com.hussainali.server.repository;

import com.hussainali.server.chat.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatMessageDaoImpl implements ChatMessageDao{

    @Autowired
    private RedisTemplate<String, Object> chatMessageRedisTemplate;


    private static final String KEY = "CHATROOM";
    @Override
    public boolean saveMessageToGroup(ChatMessage chatMessage){
        try {
            chatMessageRedisTemplate.opsForZSet().add(KEY, chatMessage,chatMessage.getCreated_at());
             return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public List<Object> fetchAllMessage(){
        List<Object> chatMessages;
        double minTimestamp = Double.NEGATIVE_INFINITY;
        double maxTimestamp = Double.POSITIVE_INFINITY;

      chatMessages = chatMessageRedisTemplate.opsForZSet().rangeByScore(KEY, minTimestamp, maxTimestamp).stream().toList();
        return chatMessages;

    }


    @Override
    public boolean deleteMessageGroup(){
        try {
            chatMessageRedisTemplate.opsForHash().delete(KEY,"MESSAGE:*");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
