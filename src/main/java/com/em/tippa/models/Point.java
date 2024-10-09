package com.em.tippa.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Points")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointID;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "groupID", nullable = false)
    private Group group;

    @Builder.Default
    @Column(nullable = false)
    private int points = 0;

    @CreationTimestamp
    private LocalDateTime creationDate;
}
