package com.example.tinder.model.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Species {
    @Id
    Long id;
    String name;
    String characteristics;


}
