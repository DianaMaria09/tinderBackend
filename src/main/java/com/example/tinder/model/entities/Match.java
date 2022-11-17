package com.example.tinder.model.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_animal1")
    Animal animal1;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_animal2")
    Animal animal2;
    Boolean likeAnimal1;
    Boolean likeAnimal2;
}
