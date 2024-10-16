package com.em.tippa.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;

@Data
@Entity
@Builder
@Table(name = "Groups")
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupID;

    @ManyToOne
    @JoinColumn(name = "creatorID", nullable = false)
    private UserEntity creator;

    @Column(nullable = false, unique = true)
    private String groupName;

    @Column(nullable = false)
    private Long maxMembers;

    @Column
    private String description;

    @ManyToOne
    private MatchList matchList;

    @CreationTimestamp
    private LocalDateTime creationDate;
}
