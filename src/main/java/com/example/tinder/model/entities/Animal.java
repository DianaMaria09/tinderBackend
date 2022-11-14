package com.example.tinder.model.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Animal {
    @Id
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
}
