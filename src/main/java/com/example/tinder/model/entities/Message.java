package com.example.tinder.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Message {
    @Id
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
