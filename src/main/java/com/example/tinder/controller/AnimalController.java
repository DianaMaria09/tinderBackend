package com.example.tinder.controller;

import com.example.tinder.model.entities.Animal;
import com.example.tinder.model.entities.User;
import com.example.tinder.service.interfaces.AnimalService;
import com.example.tinder.service.interfaces.BreedService;
import com.example.tinder.service.interfaces.SpeciesService;
import com.example.tinder.service.interfaces.UserService;
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
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE)
@CrossOrigin(origins = "*")
public class AnimalController {
    final AnimalService animalService;
    final UserService userService;
    final BreedService breedService;
    final SpeciesService speciesService;
    
    @Autowired
    public AnimalController(AnimalService animalService, UserService userService, BreedService breedService, SpeciesService speciesService) {
        this.animalService = animalService;
        this.userService = userService;
        this.breedService = breedService;
        this.speciesService = speciesService;
    }

    @RequestMapping(value = "/animals/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAnimals(@PathVariable Long userId) {
        log.info("AnimalController: list animals");
        Optional<User> user = userService.getById(userId);
        if (user.isPresent()) {
            List<Animal> response = animalService.getAllByUser(user.get());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body("User does not exist");
        }
    }

    @RequestMapping(value = "/animal/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getSelectedAnimal(@PathVariable Long userId) {
        log.info("AnimalController: selected animal");
        Optional<User> user = userService.getById(userId);
        if (user.isPresent()) {
            Animal response = animalService.getSelectedAnimalOfUser(user.get());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body("User does not exist");
        }
    }
    
    @RequestMapping(value = "/animal/get_species", method = RequestMethod.GET)
    public  ResponseEntity<?> getAnimalSpecies(){
        log.info("AnimalController: get species");
        return ResponseEntity.status(HttpStatus.OK).body(speciesService.getAll());
    }
    
    @RequestMapping(value = "/animal/get_breeds", method = RequestMethod.GET)
    public ResponseEntity<?> getAnimalBreeds(){
        log.info("AnimalController: get breeds");
        return ResponseEntity.status(HttpStatus.OK).body(breedService.getAll());
    }
}
