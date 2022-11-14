package com.example.tinder.model.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Match {
    @Id
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_animal1")
    Animal animal1;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_animal2")
    Animal animal2;
    Boolean likeValidation;
}
