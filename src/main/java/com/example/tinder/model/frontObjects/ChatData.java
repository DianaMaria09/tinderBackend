package com.example.tinder.model.frontObjects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatData {
    Long chatId;
    String url;
    String name;
    String lastMessage;
    
    public ChatData(){
    }
}
