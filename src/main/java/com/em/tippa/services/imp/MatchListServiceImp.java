package com.em.tippa.services.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.tippa.dto.CreateMatchListDto;
import com.em.tippa.dto.GetMatchListDto;
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
    public List<GetMatchListDto> findAllMatches() {
        // System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        List<MatchList> matchLists = matchListRepository.findAll();
        return matchLists.stream().map((match) -> setToMatchListDto(match)).collect(Collectors.toList());
    }

    private GetMatchListDto setToMatchListDto(MatchList match) {
        return GetMatchListDto.builder()
                .matchId(match.getMatchId())
                .team1(match.getTeam1())
                .team2(match.getTeam2())
                .tvChannel(match.getTvChannel())
                .matchTime(match.getMatchTime())
                .matchDate(match.getMatchDate())
                .result(match.getResult())
                .build();

    }

    private MatchList setToMatchList(CreateMatchListDto matchDto) {
        return MatchList.builder()
                .team1(matchDto.getTeam1())
                .team2(matchDto.getTeam2())
                .tvChannel(matchDto.getTvChannel())
                .matchTime(matchDto.getMatchTime())
                .matchDate(matchDto.getMatchDate())
                .build();

    }

    @Override
    public MatchList saveMatch(CreateMatchListDto match) {
        MatchList m = setToMatchList(match);
        return matchListRepository.save(m);
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
    public List<GetMatchListDto> findAllByOrderByDateFieldDesc() {
        List<MatchList> matchLists = matchListRepository.findAllByOrderByMatchDateDesc();
        return matchLists.stream().map((match) -> setToMatchListDto(match)).collect(Collectors.toList());
    }
}
