package com.em.tippa.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.ArrayList;
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
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id") })

    private List<Role> roles = new ArrayList<>();
}
