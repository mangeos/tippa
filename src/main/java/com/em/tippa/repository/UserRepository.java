package com.em.tippa.repository;

import com.em.tippa.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // Lägg till anpassade metoder här om du behöver ytterligare
    UserEntity findByEmail(String email);

    UserEntity findByUsername(String userName);

    UserEntity findFirstByUsername(String username);

    Optional<UserEntity> findById(Long id);

}
