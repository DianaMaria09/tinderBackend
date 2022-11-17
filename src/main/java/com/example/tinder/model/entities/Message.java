package com.example.tinder.model.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String text;
    LocalDate date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "from_animal")
    Animal fromAnimal;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "to_animal")
    Animal toAnimal;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat")
    Chat chat;
}
