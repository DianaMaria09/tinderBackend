package com.example.tinder.service.implementations;

import com.example.tinder.model.entities.Animal;
import com.example.tinder.model.entities.Breed;
import com.example.tinder.model.entities.Species;
import com.example.tinder.repository.AnimalRepository;
import com.example.tinder.service.interfaces.AnimalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class AnimalServiceImpl implements AnimalService {
    AnimalRepository animalRepository;

    List<Animal> getAll () {
        return animalRepository.findAll();
    }

    List<Animal> getAllByBreed (Breed breed) {

        Predicate<Animal> isOfBreed = animal -> animal.getBreed() == breed;

        return (List<Animal>) animalRepository.findAll().stream().filter(isOfBreed);
    }

    List<Animal> getAllBySpecies (Species species) {

        Predicate<Animal> isOfSpecies = animal -> animal.getSpecies() == species;

        return (List<Animal>) animalRepository.findAll().stream().filter(isOfSpecies);
    }

    List<Animal> getAllByBreedAndSpecies (Breed breed, Species species) {
        Predicate<Animal> isOfBreed = animal -> animal.getBreed() == breed;
        Predicate<Animal> isOfSpecies = animal -> animal.getSpecies() == species;

        Predicate<Animal> filterByAll = isOfBreed.and(isOfSpecies);

        return (List<Animal>) animalRepository.findAll().stream().filter(filterByAll);
    }
}
