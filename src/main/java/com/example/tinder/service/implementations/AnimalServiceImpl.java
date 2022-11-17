package com.example.tinder.service.implementations;

import com.example.tinder.model.entities.Animal;
import com.example.tinder.repository.AnimalRepository;
import com.example.tinder.service.interfaces.AnimalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {
    AnimalRepository animalRepository;

    List<Animal> getAll () {
        return animalRepository.findAll();
    }
}
