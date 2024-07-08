package com.em.tippa.services;

import java.util.List;

import com.em.tippa.dto.CreateMatchListDto;
import com.em.tippa.dto.GetMatchListDto;
import com.em.tippa.models.MatchList;

public interface MatchListService {
    List<GetMatchListDto> findAllMatches();

    List<GetMatchListDto> findAllByOrderByDateFieldDesc();

    MatchList saveMatch(CreateMatchListDto match);

    MatchList findMatchById(Long id);

    void deleteMatchById(Long id);

    void updateMatchById(Long id);

}
