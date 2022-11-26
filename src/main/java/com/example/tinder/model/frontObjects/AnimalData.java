package com.example.tinder.model.frontObjects;

import com.example.tinder.model.entities.Animal;
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
    String url;
    
    public AnimalData(){}
    
    public AnimalData (Animal animal){
        id = animal.getId();
        name = animal.getName();
        birthday = animal.getBirthday();
        gender = animal.getGender();
        userId = animal.getUser().getId();
        speciesId = animal.getSpecies().getId();
        breedId = animal.getBreed().getId();
        selected = animal.getSelected();
        if(animal.getImage()!=null) {
            url = new String(animal.getImage());
        }
    }
}
