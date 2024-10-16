package com.em.tippa.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.em.tippa.models.Match;
import com.em.tippa.models.MatchList;

@Repository
public interface MatchListRepository extends JpaRepository<MatchList, Long> {
    // Lägg till anpassade metoder här om du behöver ytterligare
    // dataåtkomstoperationer för Membership-modellen
    @SuppressWarnings("null")
    List<MatchList> findAll();

    @Query("SELECT m FROM MatchList ml JOIN ml.matches m WHERE ml.id = :id")
    List<Match> findMatchesByMatchListId(Long id);

}