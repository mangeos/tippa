package com.em.tippa.services.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.tippa.dto.CreateMatchDto;
import com.em.tippa.dto.GetMatchListDto;
import com.em.tippa.models.Match;
import com.em.tippa.models.MatchList;
import com.em.tippa.repository.MatchListRepository;
import com.em.tippa.repository.MatchRepository;
import com.em.tippa.services.MatchService;

@Service
public class MatchServiceImp implements MatchService {
    private MatchRepository matchRepository;

    @Autowired
    public MatchServiceImp(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public List<GetMatchListDto> findAllMatches() {
        List<Match> matchLists = matchRepository.findAll();
        return matchLists.stream().map((match) -> setToMatchListDto(match)).collect(Collectors.toList());
    }

    private GetMatchListDto setToMatchListDto(Match match) {
        return GetMatchListDto.builder()
                .matchId(match.getMatchId())
                .team1(match.getTeam1())
                .team2(match.getTeam2())
                .matchDate(match.getMatchDate())
                .build();

    }

    private Match setToMatchList(CreateMatchDto matchDto) {
        return Match.builder()
                .team1(matchDto.getTeam1())
                .team2(matchDto.getTeam2())
                .matchDate(matchDto.getMatchDate())
                .build();
    }

    @Override
    public Match saveMatch(CreateMatchDto match) {
        Match saveMatch = setToMatchList(match);
        return matchRepository.save(saveMatch);
    }

    @Override
    public Match findMatchById(Long id) {

        return matchRepository.findByMatchId(id);
    }

    @Override
    public void deleteMatchById(Long id) {
        matchRepository.deleteById(id);
    }

    @Override
    public void updateMatchById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMatchById'");
    }

    @Override
    public List<GetMatchListDto> findAllByOrderByDateFieldDesc() {
        List<Match> matchLists = matchRepository.findAllByOrderByMatchDateDesc();
        return matchLists.stream().map((match) -> setToMatchListDto(match)).collect(Collectors.toList());
    }
}
