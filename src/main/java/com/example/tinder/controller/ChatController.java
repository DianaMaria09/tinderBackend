package com.example.tinder.controller;

import com.example.tinder.model.frontObjects.ChatData;
import com.example.tinder.model.payload.SendMessageRequest;
import com.example.tinder.service.interfaces.AnimalService;
import com.example.tinder.service.interfaces.ChatService;
import com.example.tinder.service.interfaces.MatchService;
import com.example.tinder.service.interfaces.MessageService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE)
@CrossOrigin(origins = "*")
public class ChatController {
    final AnimalService animalService;
    final ChatService chatService;
    final MatchService matchService;
    final MessageService messageService;
    
    @Autowired
    public ChatController(AnimalService animalService, ChatService chatService, MatchService matchService, MessageService messageService){
    
        this.animalService = animalService;
        this.chatService = chatService;
        this.matchService = matchService;
        this.messageService = messageService;
    }
    
    @RequestMapping(value = "create_chat/{matchId}", method = RequestMethod.GET)
    public ResponseEntity<?> createChat(@PathVariable Long matchId){
        var match = matchService.getMatchById(matchId);
        var response = chatService.add(match);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @RequestMapping(value = "getChats/{animalId}", method = RequestMethod.GET)
    public ResponseEntity<?> getChats(@PathVariable Long animalId){
        var animal = animalService.findById(animalId);
        var chats = chatService.getAllForAnimal(animal);
        var response = new ArrayList<ChatData>();
        chats.forEach(chat -> {
            var chData = new ChatData();
            chData.setChatId(chat.getId());
            var secondAnimal = chat.getMatch().getAnimal1();
            if(secondAnimal.getId() == animalId){
                secondAnimal = chat.getMatch().getAnimal2();
            }
            chData.setName(secondAnimal.getName());
            if(secondAnimal.getImage()!=null){
                chData.setUrl(new String(secondAnimal.getImage()));
            }
           var message = messageService.getLastMessageForChat(chat.getId());
            if(message != null){
                chData.setLastMessage(message.getText());
            }
            response.add(chData);
        });
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = "send_message")
    public ResponseEntity<?> sendMessage(@RequestBody SendMessageRequest messageRequest){
        messageService.sendMessage(messageRequest);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @RequestMapping(value = "get_messages/{chat_id}")
    public ResponseEntity<?> getMessagesForChat(@PathVariable Long chat_id){
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getMessagesForChat(chat_id));
    }
}
