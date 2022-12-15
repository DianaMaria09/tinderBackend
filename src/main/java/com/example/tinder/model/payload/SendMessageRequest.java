package com.example.tinder.model.payload;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SendMessageRequest {
    Long chatId;
    Long animalId;
    String message;
}
