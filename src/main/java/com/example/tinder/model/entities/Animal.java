package com.example.tinder.model.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.ALWAYS)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Animal implements Serializable {
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
    @Lob
    byte[] image;

    public Animal(String name, LocalDate birthday, String gender, User user, Species species, Breed breed, Boolean selected, byte[] image) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.user = user;
        this.species = species;
        this.breed = breed;
        this.selected = selected;
        this.image = image;
    }

}
