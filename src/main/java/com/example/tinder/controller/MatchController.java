package com.example.tinder.controller;

import com.example.tinder.model.entities.Animal;
import com.example.tinder.model.entities.Match;
import com.example.tinder.model.frontObjects.AnimalData;
import com.example.tinder.service.interfaces.AnimalService;
import com.example.tinder.service.interfaces.MatchService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "/get_matches/{animal_id}")
    public ResponseEntity<?> getMatchesForAnimal(@PathVariable Long animal_id){
        log.info("MatchController: get matcher for animal with id: " + animal_id);
        Animal animal = animalService.findById(animal_id);
        List<Match> matches = matchService.getMatchForAnimal(animal);
        List<AnimalData> animalsFromMatches = new ArrayList<>();
        for(Match match : matches){
            if(match.getAnimal2() != animal){
                animalsFromMatches.add(new AnimalData(match.getAnimal2()));
            }
            else if(match.getAnimal1() != animal){
                animalsFromMatches.add(new AnimalData(match.getAnimal1()));
            }
        }
            return ResponseEntity.status(HttpStatus.OK).body(animalsFromMatches);
    }
}
