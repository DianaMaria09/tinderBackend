package com.example.tinder.service.interfaces;

import com.example.tinder.model.entities.Animal;

import java.util.List;

public interface MatchingService {
    List<Animal> getAllPossibleMatches(Animal animal);
}
