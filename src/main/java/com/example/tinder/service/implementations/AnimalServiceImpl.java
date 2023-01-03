package com.example.tinder.service.implementations;

import com.example.tinder.model.entities.Animal;
import com.example.tinder.model.entities.Breed;
import com.example.tinder.model.entities.Species;
import com.example.tinder.model.entities.User;
import com.example.tinder.repository.AnimalRepository;
import com.example.tinder.repository.ChatRepository;
import com.example.tinder.repository.MatchRepository;
import com.example.tinder.repository.MessageRepository;
import com.example.tinder.service.interfaces.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AnimalServiceImpl implements AnimalService {
    AnimalRepository animalRepository;
    MatchRepository matchRepository;
    ChatRepository chatRepository;
    MessageRepository messageRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository, MatchRepository matchRepository, ChatRepository chatRepository, MessageRepository messageRepository) {
        this.animalRepository = animalRepository;
        this.matchRepository = matchRepository;
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
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

        return animalRepository.findAll().stream().filter(filterByAll).collect(Collectors.toList());
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

        return allAnimalsBySpecies.stream().filter(areNotInPreviousMatch).collect(Collectors.toList());
    }

    @Override
    public Animal addAnimal(Optional<Long> id, String name, LocalDate birthday, String gender, User user, Species species, Breed breed, String url) {
        Animal newAnimal;
        newAnimal = id.map(aLong -> new Animal(aLong, name, birthday, gender, user, species, breed, true, url.getBytes())).orElseGet(() -> new Animal(name, birthday, gender, user, species, breed, true, url.getBytes()));
        var allAnimalsOfUser = getAllByUser(user);
        for (var animal : allAnimalsOfUser) {
            animal.setSelected(false);
        }
        animalRepository.saveAll(allAnimalsOfUser);
        return animalRepository.save(newAnimal);
    }
    
    public Animal selectAnimalForUserById(Long id, User user){
        var allAnimalsOfUser = getAllByUser(user);
        Animal retAnimal = null;
        for(var animal: allAnimalsOfUser) {
            if(animal.getId() == id){
                animal.setSelected(true);
                retAnimal = animal;
            } else {
                animal.setSelected(false);
            }
        }
        animalRepository.saveAll(allAnimalsOfUser);
        return retAnimal;
    }

    @Override
    public Animal findById(Long animalId) {
        if(animalRepository.findById(animalId).isPresent())
            return animalRepository.findById(animalId).get();
        return null;
    }

    public Animal deleteAnimal(Long id,User user) {
        var matches = matchRepository.findAll();
        for(var match : matches){
            if(Objects.equals(match.getAnimal1().getId(), id) || Objects.equals(match.getAnimal2().getId(), id)){
                var chats = chatRepository.findAll();
                for(var chat : chats){
                    if(Objects.equals(chat.getMatch().getId(), match.getId())){
                        var messages = messageRepository.findAll();
                        for(var message : messages){
                            if(Objects.equals(message.getChat().getId(), chat.getId())){
                                messageRepository.delete(message);
                            }
                        }
                        chatRepository.delete(chat);
                    }
                }
                matchRepository.delete(match);
            }
        }
        animalRepository.deleteById(id);
        var allAnimals = getAllByUser(user);
        var animal = allAnimals.get(0);
        animal.setSelected(true);
        animalRepository.save(animal);
        return animal;
    }
}
