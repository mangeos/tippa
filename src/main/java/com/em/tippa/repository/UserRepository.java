package com.em.tippa.repository;

import com.em.tippa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Lägg till anpassade metoder här om du behöver ytterligare
    User findByEmail(String email);

    User findByUsername(String userName);

}
