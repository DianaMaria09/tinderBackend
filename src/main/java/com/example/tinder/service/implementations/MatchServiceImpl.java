package com.example.tinder.service.implementations;

import com.example.tinder.model.entities.Animal;
import com.example.tinder.model.entities.Match;
import com.example.tinder.repository.MatchRepository;
import com.example.tinder.service.interfaces.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    MatchRepository matchRepository;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> getAll(){
        return matchRepository.findAll();
    }

    public List<Match> getMatchForAnimal(Animal animal){
        return matchRepository.findAll().stream()
                .filter(match -> ((match.getAnimal1() == animal || match.getAnimal2() == animal)
                && (match.getLikeAnimal1() && match.getLikeAnimal2()))).collect(Collectors.toList());
    }

    private Match getMatchBetween(Animal animal1, Animal animal2) {
        var allMatches = matchRepository.findAll();

        for (var match : allMatches) {
            if ((match.getAnimal1() == animal1 && match.getAnimal2() == animal2) ||
                    (match.getAnimal1() == animal2 && match.getAnimal2() == animal1)) {
                return match;
            }
        }
        return null;
    }

    @Override
    public void addMatch(Animal animalWhoLiked, Animal animalLikedBy) {
        var match = getMatchBetween(animalWhoLiked, animalLikedBy);

        if (match != null) {
            match.setLikeAnimal1(true);
            match.setLikeAnimal2(true);
            matchRepository.save(match);
        } else {
            var newMatch = new Match(animalWhoLiked, animalLikedBy);
            newMatch.setLikeAnimal1(true);
            matchRepository.save(newMatch);
        }
    }
}
