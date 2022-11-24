package com.example.tinder.model.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Match implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_animal1")
    Animal animal1;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_animal2")
    Animal animal2;
    @Value("${likeAnimal1:#{null}}")
    Boolean likeAnimal1;
    @Value("${likeAnimal2:#{null}}")
    Boolean likeAnimal2;

    public Match() {

    }

    public Match(Animal animal1, Animal animal2) {
        this.animal1 = animal1;
        this.animal2 = animal2;
    }
}
