package com.em.tippa.services;

import com.em.tippa.dto.RegistrationDto;
import com.em.tippa.models.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    User findByEmail(String email);

    User findByUsername(String username);
}
