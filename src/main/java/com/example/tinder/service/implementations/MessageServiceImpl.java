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
        Animal from = new Animal();
        Animal to = new Animal();
        var animal1 = chat.getMatch().getAnimal1();
        var animal2 = chat.getMatch().getAnimal2();
        var message = new Message();
        message.setChat(chat);
        message.setDate(LocalDate.now());
        message.setText(sendMessageRequest.getMessage());
        if(animal1.getId() == sendMessageRequest.getAnimalId()){
            from = animal1;
            to = animal2;
        }
        message.setFromAnimal(from);
        message.setToAnimal(to);
        messageRepository.save(message);
    }

    @Override
    public List<Message> getMessagesForChat(Long id) {
        return messageRepository.findAll().stream()
                .filter(message -> message.getChat().getId() == id)
                .sorted(Comparator.comparing(Message::getDate).reversed())
                .toList();
    }

    @Override
    public Message getLastMessageForChat(Long id) {
        var messages = getMessagesForChat(id);
        if(!messages.isEmpty())
            return getMessagesForChat(id).get(0);
        return null;
    }


}
