package com.example.tinder.service.interfaces;

import com.example.tinder.model.entities.Species;

import java.util.List;

public interface SpeciesService {
    List<Species> getAll();
    Species getById(Long id);
}
