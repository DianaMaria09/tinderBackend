package com.example.tinder.model.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    LocalDate birthday;
    String gender;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_user")
    User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "species")
    Species species;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breed")
    Breed breed;
    Boolean selected;

    public Animal(String name, LocalDate birthday, String gender, User user, Species species, Breed breed, Boolean selected) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.user = user;
        this.species = species;
        this.breed = breed;
        this.selected = selected;
    }

}
