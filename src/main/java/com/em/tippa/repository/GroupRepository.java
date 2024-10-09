package com.em.tippa.repository;

import com.em.tippa.models.Group;
import com.em.tippa.models.Match;
import com.em.tippa.models.UserEntity;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    // Lägg till anpassade metoder här om du behöver ytterligare
    // dataåtkomstoperationer för Group-modellen
    // @Query("SELECT g FROM Group g WHERE g.userId = :id")
    // List<Group> findByCreateID(@Param("id") UserEntity id);
}
