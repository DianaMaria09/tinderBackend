package com.example.tinder.service.implementations;

import com.example.tinder.model.entities.Animal;
import com.example.tinder.model.entities.Breed;
import com.example.tinder.model.entities.Species;
import com.example.tinder.model.entities.User;
import com.example.tinder.repository.AnimalRepository;
import com.example.tinder.service.interfaces.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class AnimalServiceImpl implements AnimalService {
    AnimalRepository animalRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> getAll () {
        return animalRepository.findAll();
    }

    public List<Animal> getAllByBreed(Breed breed) {

        Predicate<Animal> isOfBreed = animal -> animal.getBreed() == breed;

        return (List<Animal>) animalRepository.findAll().stream().filter(isOfBreed);
    }

    public List<Animal> getAllBySpecies (Species species) {

        Predicate<Animal> isOfSpecies = animal -> animal.getSpecies() == species;

        return (List<Animal>) animalRepository.findAll().stream().filter(isOfSpecies);
    }

    public List<Animal> getAllByBreedAndSpecies (Breed breed, Species species) {
        Predicate<Animal> isOfBreed = animal -> animal.getBreed() == breed;
        Predicate<Animal> isOfSpecies = animal -> animal.getSpecies() == species;

        Predicate<Animal> filterByAll = isOfBreed.and(isOfSpecies);

        return (List<Animal>) animalRepository.findAll().stream().filter(filterByAll);
    }

    public List<Animal> getAllByUser (User user) {
        Predicate<Animal> isOfUser = animal -> animal.getUser() == user;

        return (List<Animal>) animalRepository.findAll().stream().filter(isOfUser);
    }

    @Override
    public Animal getSelectedAnimalOfUser(User user) {
        Predicate<Animal> isSelected = animal -> animal.getSelected() == Boolean.TRUE;

        return (Animal) getAllByUser(user).stream().filter(isSelected);
    }
}
