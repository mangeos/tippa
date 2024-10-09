package com.em.tippa.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class CreateMatchDto {

    @NotEmpty(message = "Lag1 kan inte vara tom")
    private String team1;

    @NotEmpty(message = "Lag2 kan inte vara tom")
    private String team2;

    @NotNull(message = "Matchdatum kan inte vara tom")
    private LocalDateTime matchDate;

}
