package com.example.tinder.model.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Breed {
    @Id
    Long id;
    String name;
}
