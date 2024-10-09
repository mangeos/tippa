package com.em.tippa.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetMatchListDto {

    private Long matchId;

    private String team1;

    private String team2;

    private LocalDateTime matchDate;
}
