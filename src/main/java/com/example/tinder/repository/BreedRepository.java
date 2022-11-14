package com.example.tinder.repository;

import com.example.tinder.model.entities.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedRepository extends JpaRepository<Breed, Long> {
}
