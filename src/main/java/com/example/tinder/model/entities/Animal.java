package com.example.tinder.model.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
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
}
