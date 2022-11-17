package com.example.tinder.service.interfaces;

import com.example.tinder.model.entities.Chat;
import com.example.tinder.model.entities.Message;

import java.util.List;

public interface MessageService {
    List<Message> getAll();
}
