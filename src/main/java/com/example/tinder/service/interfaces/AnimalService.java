package com.example.tinder.service.interfaces;

import com.example.tinder.model.entities.Animal;
import com.example.tinder.model.entities.Breed;
import com.example.tinder.model.entities.Species;
import com.example.tinder.model.entities.User;

import java.util.List;

public interface AnimalService {
    List<Animal> getAll();

    List<Animal> getAllByBreed(Breed breed);
    List<Animal> getAllBySpecies(Species species);
    List<Animal> getAllByBreedAndSpecies(Breed breed, Species species);
    List<Animal> getAllByUser(User user);
    Animal getSelectedAnimalOfUser(User user);

}
