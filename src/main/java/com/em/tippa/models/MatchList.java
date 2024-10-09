package com.em.tippa.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.*;

import lombok.Builder;

@Data
@Entity
@Builder
@Table(name = "Match_list")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "matches", "Groupes" })
public class MatchList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchListId;

    @Column(unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "match_list_match", joinColumns = @JoinColumn(name = "match_list_id"), inverseJoinColumns = @JoinColumn(name = "match_id"))
    private List<Match> matches = new ArrayList<>();

    @OneToMany(mappedBy = "matchList")
    private List<Group> Groupes = new ArrayList<>();

}
