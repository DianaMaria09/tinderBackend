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

    /**
     * Function to call when you want all of the possible matches you can show to a
     * certain Animal (no previous match)
     * @return List<Animal>
     */
    List<Animal> getAllToDisplayForAnimal(Animal animal);

}
