package com.example.tinder.service.implementations;

import com.example.tinder.model.entities.Chat;
import com.example.tinder.repository.ChatRepository;
import com.example.tinder.service.interfaces.ChatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    ChatRepository chatRepository;

    List<Chat> getAll() {
        return chatRepository.findAll();
    }
}
