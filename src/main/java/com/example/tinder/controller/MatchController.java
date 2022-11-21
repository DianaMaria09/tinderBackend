package com.example.tinder.controller;

import com.example.tinder.service.interfaces.AnimalService;
import com.example.tinder.service.interfaces.MatchService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE)
@CrossOrigin(origins = "*")
public class MatchController {
    final MatchService matchService;
    final AnimalService animalService;

    @Autowired
    public MatchController(MatchService matchService, AnimalService animalService) {
        this.matchService = matchService;
        this.animalService = animalService;
    }
}
