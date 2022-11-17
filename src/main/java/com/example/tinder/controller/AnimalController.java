package com.example.tinder.controller;

import com.example.tinder.model.entities.Animal;
import com.example.tinder.service.interfaces.AnimalService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnimalController {
    final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @RequestMapping(value = "/animals/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAnimals(@PathVariable Long userId){
        log.info("AnimalController: list animals");
        List<Animal> response = animalService.getAll();
        return ResponseEntity.ok(response);
    }
}
