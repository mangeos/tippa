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
@Table(name = "Match_list")
@NoArgsConstructor
@AllArgsConstructor
public class MatchList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;

    @Column(nullable = false)
    private String team1;

    @Column(nullable = false)
    private String team2;

    @Column(nullable = false)
    private String tvChannel;

    @Column(nullable = false)
    private String matchTime;

    @Column(nullable = false)
    private LocalDate matchDate;

    @Builder.Default
    private String result = "0-0";

}
