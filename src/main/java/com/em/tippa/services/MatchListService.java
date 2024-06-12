package com.em.tippa.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.em.tippa.dto.MatchListDto;
import com.em.tippa.models.MatchList;

public interface MatchListService {
    List<MatchListDto> findAllMatches();

    List<MatchListDto> findAllByOrderByDateFieldDesc();

    MatchList saveMatch(MatchList match);

    MatchList findMatchById(Long id);

    void deleteMatchById(Long id);

    void updateMatchById(Long id);

}
