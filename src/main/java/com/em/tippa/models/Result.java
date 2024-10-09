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
@Table(name = "Result")
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;

    @OneToOne
    private Match match;

    @Column(nullable = false)
    private String team1Goal;

    @Column(nullable = false)
    private String team2Goal;
}
