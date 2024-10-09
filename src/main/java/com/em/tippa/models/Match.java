package com.em.tippa.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.*;

import lombok.Builder;

@Data
@Entity
@Builder
@Table(name = "Match")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "matchLists")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;

    @Column(nullable = false)
    private String team1;

    @Column(nullable = false)
    private String team2;

    @Column(nullable = false)
    private LocalDateTime matchDate;

    @OneToMany(mappedBy = "match")
    private List<Guess> guesses;

    @OneToOne(mappedBy = "match")
    private Result result;

    @ManyToMany(mappedBy = "matches", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MatchList> matchLists = new ArrayList<>();

}
