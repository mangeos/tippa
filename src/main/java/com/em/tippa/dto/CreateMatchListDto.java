package com.em.tippa.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateMatchListDto {

    @NotEmpty(message = "Lag1 kan inte vara tom")
    private String team1;

    @NotEmpty(message = "Lag2 kan inte vara tom")
    private String team2;

    private String tvChannel;

    @NotEmpty(message = "Matchtid kan inte vara tom")
    private String matchTime;

    @NotNull(message = "Matchdatum kan inte vara tom")
    private LocalDate matchDate;

}
