package com.example.tinder.controller;

import com.example.tinder.model.entities.Animal;
import com.example.tinder.model.frontObjects.AnimalData;
import com.example.tinder.service.interfaces.AnimalService;
import com.example.tinder.service.interfaces.MatchingService;
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
public class MatchingController {
    final AnimalService animalService;
    final MatchingService matchingService;

    @Autowired
    public MatchingController(AnimalService animalService, MatchingService matchingService) {
        this.animalService = animalService;
        this.matchingService = matchingService;
    }

    @RequestMapping(value = "/get_matching/{animal_id}")
    public ResponseEntity<?> getPossibleMatches(@PathVariable Long animal_id){
        log.info("MatchingController get possible matches for animal with id: " + animal_id);
        List <AnimalData> frontAnimals = new ArrayList<>();
        List<Animal> animals = matchingService.getAllPossibleMatches(animalService.findById(animal_id));
        for(Animal animal : animals){
            frontAnimals.add(new AnimalData(animal));
        }
        return ResponseEntity.status(HttpStatus.OK).body(frontAnimals);
    }
}
