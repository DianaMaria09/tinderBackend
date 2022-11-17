package com.example.tinder.service.implementations;

import com.example.tinder.model.entities.Breed;
import com.example.tinder.repository.BreedRepository;
import com.example.tinder.service.interfaces.BreedService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreedServiceImpl implements BreedService {

    BreedRepository breedRepository;

    List<Breed> getAll () {
        return breedRepository.findAll();
    }
}
