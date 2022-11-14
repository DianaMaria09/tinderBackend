package com.example.tinder.model.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Chat {
    @Id
    Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    Match match;
}
