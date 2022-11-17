package com.example.tinder.service.implementations;

import com.example.tinder.model.entities.Message;
import com.example.tinder.repository.MessageRepository;
import com.example.tinder.service.interfaces.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAll() {
        return messageRepository.findAll();
    }
}
