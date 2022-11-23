package com.example.tinder.service.implementations;

import com.example.tinder.model.entities.Animal;
import com.example.tinder.model.entities.Breed;
import com.example.tinder.model.entities.Species;
import com.example.tinder.model.entities.User;
import com.example.tinder.repository.AnimalRepository;
import com.example.tinder.repository.MatchRepository;
import com.example.tinder.service.interfaces.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AnimalServiceImpl implements AnimalService {
    AnimalRepository animalRepository;
    MatchRepository matchRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> getAll () {
        return animalRepository.findAll();
    }

    public List<Animal> getAllByBreed(Breed breed) {

        Predicate<Animal> isOfBreed = animal -> animal.getBreed() == breed;

        return animalRepository.findAll().stream().filter(isOfBreed).collect(Collectors.toList());
    }

    public List<Animal> getAllBySpecies (Species species) {

        Predicate<Animal> isOfSpecies = animal -> animal.getSpecies() == species;

        return animalRepository.findAll().stream().filter(isOfSpecies).collect(Collectors.toList());
    }

    public List<Animal> getAllByBreedAndSpecies (Breed breed, Species species) {
        Predicate<Animal> isOfBreed = animal -> animal.getBreed() == breed;
        Predicate<Animal> isOfSpecies = animal -> animal.getSpecies() == species;

        Predicate<Animal> filterByAll = isOfBreed.and(isOfSpecies);

        return (List<Animal>) animalRepository.findAll().stream().filter(filterByAll);
    }

    public List<Animal> getAllByUser (User user) {
        Predicate<Animal> isOfUser = animal -> animal.getUser() == user;

        return animalRepository.findAll().stream().filter(isOfUser).collect(Collectors.toList());
    }

    @Override
    public Animal getSelectedAnimalOfUser(User user) {
        Predicate<Animal> isSelected = animal -> animal.getSelected() == Boolean.TRUE;

        return getAllByUser(user).stream().filter(isSelected).collect(Collectors.toList()).get(0);
    }

    /***
     * check if animal1 already matched with animal2 (either liked or disliked animal2)
     * @param animal1
     * @param animal2
     * @return true, if animal1 liked or disliked animal2,
     *          false, if animal1 didn't interact with animal2 yet
     */
    private boolean areAnimalsInAMatch(Animal animal1, Animal animal2) {
        var allMatches = matchRepository.findAll();

        for (var match : allMatches) {
            if ((match.getAnimal1() == animal1 && match.getAnimal2() == animal2) ||
                    (match.getAnimal1() == animal2 && match.getAnimal2() == animal1 && match.getLikeAnimal2() != null)) {
                return true;
            }
        }

        return false;
    }
    @Override
    public List<Animal> getAllToDisplayForAnimal(Animal animal) {

        Predicate<Animal> areNotInPreviousMatch = animalPrev -> !areAnimalsInAMatch(animal, animalPrev);

        var allAnimalsBySpecies = getAllBySpecies(animal.getSpecies());

        return (List<Animal>) allAnimalsBySpecies.stream().filter(areNotInPreviousMatch);
    }

    @Override
    public Animal addAnimal(Optional<Long> id, String name, LocalDate birthday, String gender, User user, Species species, Breed breed) {
        Animal newAnimal;
        newAnimal = id.map(aLong -> new Animal(aLong, name, birthday, gender, user, species, breed, true)).orElseGet(() -> new Animal(name, birthday, gender, user, species, breed, true));
        var allAnimalsOfUser = getAllByUser(user);
        for (var animal : allAnimalsOfUser) {
            animal.setSelected(false);
        }
        animalRepository.saveAll(allAnimalsOfUser);
        return animalRepository.save(newAnimal);
    }

    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }
}
