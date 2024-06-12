package com.em.tippa.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.Builder;

@Builder
@Data // Lombok-annotation för att generera getters, setters, toString, equals och
public class UserDto {

    private Long userID;

    private String username;

    private String email;

    private String password;

    private String phoneNumber;

}
