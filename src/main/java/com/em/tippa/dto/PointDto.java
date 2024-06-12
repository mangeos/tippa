package com.em.tippa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import lombok.Builder;

@Builder
@Data
public class PointDto {

    private Long pointID;

    private int points;

    private Date updateDate;
}
