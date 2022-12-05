package com.example.tinder.service.implementations;

import com.example.tinder.model.entities.Animal;
import com.example.tinder.model.entities.Chat;
import com.example.tinder.model.entities.Match;
import com.example.tinder.model.entities.Message;
import com.example.tinder.repository.ChatRepository;
import com.example.tinder.repository.MatchRepository;
import com.example.tinder.service.interfaces.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    ChatRepository chatRepository;
    MatchRepository matchRepository;

    List<Message> messages;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository, MatchRepository matchRepository, List<Message> messages) {
        this.chatRepository = chatRepository;
        this.matchRepository = matchRepository;
        this.messages = messages;
    }

    public List<Chat> getAll() {
        return chatRepository.findAll();
    }

    @Override
    public void addMessage(Message message) {
        this.messages.add(message);
    }

    @Override
    public void deleteMessage(Message message) {
        this.messages.remove(message);
    }

    private Chat getChatBetween(Animal animal1, Animal animal2) {
        var allChats = chatRepository.findAll();

        for (var chat : allChats) {
            if ((chat.getMatch().getAnimal1() == animal1 && chat.getMatch().getAnimal2() == animal2) ||
                    (chat.getMatch().getAnimal1() == animal2 && chat.getMatch().getAnimal2() == animal1)) {
                return chat;
            }
        }
        return null;
    }
    public void addChat(Animal animalWhoLiked, Animal animalLikedBy) {
        Chat chat = new Chat();
        List<Match> allMatch = matchRepository.findAll();
        for (var match : allMatch) {
            if (match.getAnimal1() == animalLikedBy && match.getAnimal2() == animalWhoLiked ||
                    match.getAnimal2() == animalLikedBy && match.getAnimal1() == animalWhoLiked) {
                chat.setMatch(new Match(animalWhoLiked, animalLikedBy));
                this.chatRepository.save(chat);
            }
        }
    }
}
