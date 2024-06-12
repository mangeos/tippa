package com.em.tippa.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.Builder;

@Data // Lombok-annotation för att generera getters, setters, toString, equals och
@Entity
@Builder
// hashCode automatiskt
@NoArgsConstructor // Lombok-annotation för att generera en standardkonstruktor utan argument
@AllArgsConstructor // Lombok-annotation för att generera en konstruktor med alla attribut
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

}
