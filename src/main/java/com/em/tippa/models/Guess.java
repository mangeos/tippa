package com.em.tippa.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import lombok.Builder;

@Data
@Entity
@Builder
@Table(name = "Guess")
@NoArgsConstructor
@AllArgsConstructor
public class Guess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guessId;

    @Column(nullable = false)
    private int team1;

    @Column(nullable = false)
    private int team2;

    @ManyToOne(optional = false)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
