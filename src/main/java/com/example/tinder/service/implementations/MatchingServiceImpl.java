package com.example.tinder.service.implementations;

import com.example.tinder.model.entities.Animal;
import com.example.tinder.model.entities.Match;
import com.example.tinder.repository.AnimalRepository;
import com.example.tinder.repository.MatchRepository;
import com.example.tinder.service.interfaces.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MatchingServiceImpl implements MatchingService {
    private AnimalRepository animalRepository;

    private MatchRepository matchRepository;
    private Integer MAX_YEAR_DIFFERENCE_BETWEEN_ANIMALS = 3;

    @Autowired
    public MatchingServiceImpl(AnimalRepository animalRepository, MatchRepository matchRepository){
        this.animalRepository = animalRepository;
        this.matchRepository = matchRepository;
    }

    public List<Animal> getAllPossibleMatches(Animal animal){
        List<Match> allMatches = matchRepository.findAll().stream()
                .filter(match -> (Objects.equals(match.getAnimal1().getId(), animal.getId()) || Objects.equals(match.getAnimal2().getId(), animal.getId()))).toList();
        List<Animal> possibleMatches = new ArrayList<>();
        if(animal == null)
            return possibleMatches;
        for(Animal animalFromPossibleMatches : animalRepository.findAll()){
            Boolean like = null;
            Boolean anotherLike = null;
            var exists = allMatches.stream().anyMatch(match ->
                    (Objects.equals(match.getAnimal2().getId(), animalFromPossibleMatches.getId())
                            || Objects.equals(match.getAnimal1().getId(), animalFromPossibleMatches.getId())));
            if(exists) {
                var matchExists = allMatches.stream()
                        .filter(match ->
                                (Objects.equals(match.getAnimal2().getId(), animalFromPossibleMatches.getId())
                                        || Objects.equals(match.getAnimal1().getId(), animalFromPossibleMatches.getId()))).toList().get(0);
                if(Objects.equals(matchExists.getAnimal1().getId(), animal.getId())){
                    like = matchExists.getLikeAnimal1();
                    anotherLike = matchExists.getLikeAnimal2();
                }
                else{
                    like = matchExists.getLikeAnimal2();
                    anotherLike = matchExists.getLikeAnimal1();
                }
            }
            if (animalFromPossibleMatches.getSpecies() == animal.getSpecies()
                    && !animalFromPossibleMatches.getGender().equals(animal.getGender())
                    && Math.abs(ChronoUnit.YEARS.between(animalFromPossibleMatches.getBirthday(), animal.getBirthday())) < MAX_YEAR_DIFFERENCE_BETWEEN_ANIMALS
                    && !Objects.equals(animalFromPossibleMatches.getId(), animal.getId())
                    && (!exists || (like == null && anotherLike))){
                possibleMatches.add(animalFromPossibleMatches);
            }
        }
        return possibleMatches;
    }
}
