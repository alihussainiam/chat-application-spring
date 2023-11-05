package com.hussainali.server.chat;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ChatMessage implements Serializable {
    private String content;
    private String sender;
    private MessageType type;
    private Double created_at;

}


