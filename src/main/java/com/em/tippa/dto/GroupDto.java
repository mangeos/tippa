package com.em.tippa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import lombok.Builder;

@Builder
@Data
public class GroupDto {

    private Long groupID;

    private String groupName;

    private String description;

    private Date creationDate;
}
