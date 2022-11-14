package com.example.tinder.repository;

import com.example.tinder.model.entities.Species;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepository extends JpaRepository<Species, Long> {
}
