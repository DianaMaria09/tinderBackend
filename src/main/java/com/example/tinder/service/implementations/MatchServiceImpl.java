package com.example.tinder.service.implementations;

import com.example.tinder.model.entities.Match;
import com.example.tinder.repository.MatchRepository;
import com.example.tinder.service.interfaces.MatchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    MatchRepository matchRepository;

    List<Match> getAll(){
        return matchRepository.findAll();
    }
}
