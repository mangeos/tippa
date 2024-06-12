package com.em.tippa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.em.tippa.models.MatchList;

@Repository
public interface MatchListRepository extends JpaRepository<MatchList, Long> {
    // Lägg till anpassade metoder här om du behöver ytterligare
    // dataåtkomstoperationer för Membership-modellen
    List<MatchList> findAllByOrderByMatchDateDesc();

    MatchList findByMatchId(Long id);

}