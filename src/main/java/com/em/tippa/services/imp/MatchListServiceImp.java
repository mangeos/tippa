package com.em.tippa.services.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.tippa.dto.MatchListDto;
import com.em.tippa.models.MatchList;
import com.em.tippa.repository.MatchListRepository;
import com.em.tippa.services.MatchListService;

@Service
public class MatchListServiceImp implements MatchListService {
    private MatchListRepository matchListRepository;

    @Autowired
    public MatchListServiceImp(MatchListRepository matchListRepository) {
        this.matchListRepository = matchListRepository;
    }

    @Override
    public List<MatchListDto> findAllMatches() {
        // System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        List<MatchList> matchLists = matchListRepository.findAll();
        return matchLists.stream().map((match) -> setToMatchListDto(match)).collect(Collectors.toList());
    }

    private MatchListDto setToMatchListDto(MatchList match) {
        return MatchListDto.builder()
                .matchId(match.getMatchId())
                .team1(match.getTeam1())
                .team2(match.getTeam2())
                .tvChannel(match.getTvChannel())
                .matchTime(match.getMatchTime())
                .matchDate(match.getMatchDate())
                .result(match.getResult())
                .build();

    }

    @Override
    public MatchList saveMatch(MatchList match) {
        return matchListRepository.save(match);
    }

    @Override
    public MatchList findMatchById(Long id) {
        MatchList matchListOptional = matchListRepository.findByMatchId(id);

        return matchListOptional;
    }

    @Override
    public void deleteMatchById(Long id) {
        matchListRepository.deleteById(id);
    }

    @Override
    public void updateMatchById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMatchById'");
    }

    @Override
    public List<MatchListDto> findAllByOrderByDateFieldDesc() {
        List<MatchList> matchLists = matchListRepository.findAllByOrderByMatchDateDesc();
        return matchLists.stream().map((match) -> setToMatchListDto(match)).collect(Collectors.toList());
    }
}
