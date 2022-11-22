package com.example.tinder.model.frontObjects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnimalData {
    Long id;
    String name;
    LocalDate birthday;
    String gender;
    Long userId;
    Long speciesId;
    Long breedId;
    Boolean selected;
}
