package com.example.tinder.service.implementations;

import com.example.tinder.model.entities.User;
import com.example.tinder.repository.UserRepository;
import com.example.tinder.service.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    List<User> getAll() {
        return userRepository.findAll();
    }
}
