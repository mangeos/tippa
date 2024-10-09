package com.em.tippa.services;

import java.util.List;

import com.em.tippa.dto.CreateMatchDto;
import com.em.tippa.dto.GetMatchListDto;
import com.em.tippa.models.Match;
import com.em.tippa.models.MatchList;

public interface MatchService {
    List<GetMatchListDto> findAllMatches();

    List<GetMatchListDto> findAllByOrderByDateFieldDesc();

    Match saveMatch(CreateMatchDto match);

    Match findMatchById(Long id);

    void deleteMatchById(Long id);

    void updateMatchById(Long id);

}
