package com.example.tinder.service.implementations;

import com.example.tinder.model.entities.Animal;
import com.example.tinder.model.entities.Match;
import com.example.tinder.repository.MatchRepository;
import com.example.tinder.service.interfaces.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
                && ((match.getLikeAnimal1() != null && match.getLikeAnimal1() == true) && (match.getLikeAnimal2() != null &&match.getLikeAnimal2() == true)) && (!match.getDeleted()))).collect(Collectors.toList());
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
    public void addMatch(Animal animalWhoLiked, Animal animalLikedBy, boolean like) {
        var match = getMatchBetween(animalWhoLiked, animalLikedBy);

        if (match == null) {
            var newMatch = new Match(animalWhoLiked, animalLikedBy);
            if(Objects.equals(newMatch.getAnimal1().getId(), animalWhoLiked.getId()))
                newMatch.setLikeAnimal1(like);
            else
                newMatch.setLikeAnimal2(like);
            matchRepository.save(newMatch);
        } else {
            if(Objects.equals(match.getAnimal1().getId(), animalWhoLiked.getId()))
                match.setLikeAnimal1(like);
            else
                match.setLikeAnimal2(like);
            matchRepository.save(match);
        }
    }
    
    @Override
    public void deleteMatch(Animal animal1, Animal animal2) {
        var match = getMatchBetween(animal1, animal2);
        match.setDeleted(true);
        matchRepository.save(match);
    }
    
    @Override
    public Match getMatchById(Long id){
        return matchRepository.findById(id).get();
    }
}
