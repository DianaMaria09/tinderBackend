package com.example.tinder.model.frontObjects;

import com.example.tinder.model.entities.Message;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageData {
    String text;
    Long from;
    
    public MessageData(Message message){
        this.text = message.getText();
        this.from = message.getFromAnimal().getId();
    }
}
