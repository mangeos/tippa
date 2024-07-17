package com.em.tippa.services;

import com.em.tippa.dto.RegistrationDto;
import com.em.tippa.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
