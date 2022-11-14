package com.example.tinder.model.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class User {
    @Id
    Long id;
    String username;
    String password;
    String phone;
    String email;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_address")
    Address address;
}
