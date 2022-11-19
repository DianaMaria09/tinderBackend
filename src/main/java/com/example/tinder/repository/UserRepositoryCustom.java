package com.example.tinder.repository;

import com.example.tinder.model.entities.User;

public interface UserRepositoryCustom {
    User findByLogin(String username, String password);
}
