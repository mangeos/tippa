package com.em.tippa.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MatchListDto {

    private Long matchId;

    private String team1;

    private String team2;

    private String tvChannel;

    private String matchTime;

    private LocalDate matchDate;

    private String result;
}
