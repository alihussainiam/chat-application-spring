package com.hussainali.server.chat;

import com.hussainali.server.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ChatController {
    @Autowired
    private ChatMessageService chatMessageService;
    @MessageMapping("/chat.sendMessage")
    @SendTo("/chatroom/public")
    public ChatMessage sendMessage(
            @Payload ChatMessage chatMessage
    ){
        boolean saved = chatMessageService.saveMessage(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/chatroom/public")
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ){
        // Adds username in websocket session
        headerAccessor.getSessionAttributes().put("username",chatMessage.getSender());
        boolean saved = chatMessageService.saveMessage(chatMessage);
        return chatMessage;
    }


    @SubscribeMapping("/chatroom/public")
    public List<Object> getInitialMessages() {
        // Create and return a list of initial messages
        // Populate the list with messages
        return chatMessageService.fetchAllMessages();
    }

}
