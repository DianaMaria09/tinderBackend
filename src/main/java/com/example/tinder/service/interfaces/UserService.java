package com.example.tinder.service.interfaces;

import com.example.tinder.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();

    Optional<User> getById(Long id);

    void addUser(Long id, String username, String password, String phone, String email, String street, String city, String country, String county);

    User getByLogin(String username, String password);
}
