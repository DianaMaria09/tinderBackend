package com.example.tinder.service.interfaces;

import com.example.tinder.model.entities.Animal;
import com.example.tinder.model.entities.Match;

import java.util.List;

public interface MatchingService {
    List<Animal> getAllPossibleMatches(Animal animal);
}
