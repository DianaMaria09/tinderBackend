package com.example.tinder.service.implementations;

import com.example.tinder.model.entities.Animal;
import com.example.tinder.model.entities.Chat;
import com.example.tinder.model.entities.Match;
import com.example.tinder.repository.ChatRepository;
import com.example.tinder.service.interfaces.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ChatServiceImpl implements ChatService {

    ChatRepository chatRepository;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public List<Chat> getAllForAnimal(Animal animal) {
        return chatRepository.findAll().stream().filter(chat -> Objects.equals(chat.getMatch().getAnimal1().getId(), animal.getId()) || Objects.equals(chat.getMatch().getAnimal2().getId(), animal.getId())).filter(chat-> !chat.getMatch().getDeleted()).toList();
    }
    
    public Long add(Match match){
        var existingChats = chatRepository.findAll().stream().filter(chat -> Objects.equals(chat.getMatch().getId(), match.getId())).toList();
        if(existingChats.size()>0){
            return  existingChats.get(0).getId();
        }
        Chat chat = new Chat();
        chat.setMatch(match);
        var result = chatRepository.save(chat);
        return result.getId();
    }
}
