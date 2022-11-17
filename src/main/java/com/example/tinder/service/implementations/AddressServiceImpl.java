package com.example.tinder.service.implementations;

import com.example.tinder.model.entities.Address;
import com.example.tinder.repository.AddressRepository;
import com.example.tinder.service.interfaces.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    AddressRepository addressRepository;

    List<Address> getAll() {
        return addressRepository.findAll();
    }

}
