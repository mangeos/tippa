package com.em.tippa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.em.tippa.models.Match;
import com.em.tippa.models.MatchList;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    // Lägg till anpassade metoder här om du behöver ytterligare
    // dataåtkomstoperationer för Membership-modellen
    List<Match> findAllByOrderByMatchDateDesc();

    Match findByMatchId(Long id);

}