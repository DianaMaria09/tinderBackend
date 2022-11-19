package com.example.tinder.service.implementations;

import com.example.tinder.model.entities.Address;
import com.example.tinder.model.entities.User;
import com.example.tinder.repository.AddressRepository;
import com.example.tinder.repository.UserRepository;
import com.example.tinder.repository.UserRepositoryCustom;
import com.example.tinder.repository.implementations.UserRepositoryCustomImpl;
import com.example.tinder.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserRepositoryCustom userRepositoryCustom;
    AddressRepository addressRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, @Qualifier("userRepositoryCustomImpl") UserRepositoryCustom userRepositoryCustom) {
        this.userRepository = userRepository;
        this.userRepositoryCustom = userRepositoryCustom;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void addUser(Long id, String username, String password, String phone, String email, String street, String city, String country, String county) {
        Address newAdress = new Address(street, city, country, county);

        User newUser = new User(username, password, phone, email, newAdress);

        if (!(userRepository.findById(id).isPresent())) {
            newUser.setId(id);
            userRepository.save(newUser);
        }
    }

    @Override
    public User getByLogin(String username, String password) {
        return userRepositoryCustom.findByLogin(username, password);
    }
}
