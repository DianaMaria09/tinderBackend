package com.example.tinder.service.interfaces;

import com.example.tinder.model.entities.Animal;
import com.example.tinder.model.entities.Breed;

import java.util.List;

public interface BreedService {
    List<Breed> getAll();
}
