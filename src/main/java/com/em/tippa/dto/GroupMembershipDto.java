package com.em.tippa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.Builder;

@Builder
@Data
public class GroupMembershipDto {

    private Long membershipID;

    private String roll;
}
