package com.example.tinder.service.implementations;

import com.example.tinder.model.entities.Animal;
import com.example.tinder.model.entities.Message;
import com.example.tinder.model.payload.SendMessageRequest;
import com.example.tinder.repository.ChatRepository;
import com.example.tinder.repository.MessageRepository;
import com.example.tinder.service.interfaces.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class MessageServiceImpl implements MessageService {
    MessageRepository messageRepository;
    ChatRepository chatRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
    }

    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public void sendMessage(SendMessageRequest sendMessageRequest) {
        var chat = chatRepository.findById(sendMessageRequest.getChatId()).get();
        Animal from, to;
        var animal1 = chat.getMatch().getAnimal1();
        var animal2 = chat.getMatch().getAnimal2();
        var message = new Message();
        message.setChat(chat);
        message.setDate(LocalDate.now());
        message.setText(sendMessageRequest.getMessage());
        if(Objects.equals(animal1.getId(), sendMessageRequest.getAnimalId())){
            from = animal1;
            to = animal2;
        } else {
            from = animal2;
            to = animal1;
        }
        message.setFromAnimal(from);
        message.setToAnimal(to);
        messageRepository.save(message);
    }

    @Override
    public List<Message> getMessagesForChat(Long id) {
        return messageRepository.findAll().stream()
                .filter(message -> Objects.equals(message.getChat().getId(), id))
                .sorted(Comparator.comparing(Message::getDate))
                .toList();
    }

    @Override
    public Message getLastMessageForChat(Long id) {
        var messages = getMessagesForChat(id);
        if(!messages.isEmpty())
            return getMessagesForChat(id).get(messages.size() - 1);
        return null;
    }


}
