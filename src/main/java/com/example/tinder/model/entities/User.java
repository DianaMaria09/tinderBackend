package com.example.tinder.model.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String username;
    String password;
    String phone;
    String email;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_address")
    Address address;

    public User(String username, String password, String phone, String email, Address address) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public User() {

    }
}
