package com.example.tinder.controller;

import com.example.tinder.model.frontObjects.ChatData;
import com.example.tinder.service.interfaces.AnimalService;
import com.example.tinder.service.interfaces.ChatService;
import com.example.tinder.service.interfaces.MatchService;
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
    
    @Autowired
    public ChatController(AnimalService animalService, ChatService chatService, MatchService matchService){
    
        this.animalService = animalService;
        this.chatService = chatService;
        this.matchService = matchService;
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
            if(secondAnimal.getId()!=null){
                chData.setUrl(new String(secondAnimal.getImage()));
            }
            response.add(chData);
        });
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
