package com.example.tinder.service.interfaces;

import com.example.tinder.model.entities.Chat;
import com.example.tinder.model.entities.Match;

import java.util.List;

public interface MatchService {
    List<Match> getAll();
}
