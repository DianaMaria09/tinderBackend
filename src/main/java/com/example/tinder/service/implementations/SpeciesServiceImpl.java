package com.example.tinder.service.implementations;

import com.example.tinder.model.entities.Species;
import com.example.tinder.repository.SpeciesRepository;
import com.example.tinder.service.interfaces.SpeciesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeciesServiceImpl implements SpeciesService {
    SpeciesRepository speciesRepository;

    List<Species> getAll() {
        return speciesRepository.findAll();
    }
}
