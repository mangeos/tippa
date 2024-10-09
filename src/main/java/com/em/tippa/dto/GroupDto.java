package com.em.tippa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.em.tippa.models.MatchList;
import com.em.tippa.models.UserEntity;

import jakarta.persistence.Column;
import lombok.Builder;

@Data
public class GroupDto {

    private UserEntity creator;

    private String groupName;

    private MatchList matchList;

}
