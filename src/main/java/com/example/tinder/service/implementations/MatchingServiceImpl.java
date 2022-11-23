package com.example.tinder.service.implementations;

import com.example.tinder.model.entities.Animal;
import com.example.tinder.model.entities.Match;
import com.example.tinder.repository.AnimalRepository;
import com.example.tinder.service.interfaces.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MatchingServiceImpl implements MatchingService {
    private AnimalRepository animalRepository;

    @Autowired
    public MatchingServiceImpl(AnimalRepository animalRepository){
        this.animalRepository = animalRepository;
    }

    public List<Animal> getAllPossibleMatches(Animal animal){

        List<Animal> possibleMatches = new ArrayList<>();
        for(Animal animalFromPossibleMatches : animalRepository.findAll()){
            if((animalFromPossibleMatches.getSpecies() == animal.getSpecies()
                    || animalFromPossibleMatches.getBreed() == animal.getBreed())
                    && Math.abs(ChronoUnit.YEARS.between(animalFromPossibleMatches.getBirthday(), animal.getBirthday())) < 3
                    && !Objects.equals(animalFromPossibleMatches.getId(), animal.getId())){
                possibleMatches.add(animalFromPossibleMatches);
            }
        }
        return possibleMatches;
    }
}
