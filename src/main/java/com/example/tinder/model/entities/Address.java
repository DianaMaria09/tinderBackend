package com.example.tinder.model.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@FieldDefaults
public class Address {
    @Id
    Long id;
    String street;
    String city;
    String country;
    String county;
}
