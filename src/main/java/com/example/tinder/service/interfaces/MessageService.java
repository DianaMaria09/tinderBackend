package com.example.tinder.service.interfaces;

import com.example.tinder.model.entities.Chat;
import com.example.tinder.model.entities.Message;
import com.example.tinder.model.payload.SendMessageRequest;

import java.util.List;

public interface MessageService {
    List<Message> getAll();
    void sendMessage(SendMessageRequest sendMessageRequest);
    List<Message> getMessagesForChat(Long id);
    Message getLastMessageForChat(Long id);
}
