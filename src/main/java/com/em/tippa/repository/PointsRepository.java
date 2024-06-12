package com.em.tippa.repository;

import com.em.tippa.models.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointsRepository extends JpaRepository<Point, Long> {
    // Lägg till anpassade metoder här om du behöver ytterligare
    // dataåtkomstoperationer för Points-modellen
}
