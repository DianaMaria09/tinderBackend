package com.example.tinder.service.implementations;

import com.example.tinder.model.entities.Breed;
import com.example.tinder.repository.BreedRepository;
import com.example.tinder.service.interfaces.BreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreedServiceImpl implements BreedService {

    BreedRepository breedRepository;

    @Autowired
    public BreedServiceImpl(BreedRepository breedRepository) {
        this.breedRepository = breedRepository;
    }

    public List<Breed> getAll() {
        return breedRepository.findAll();
    }
}
