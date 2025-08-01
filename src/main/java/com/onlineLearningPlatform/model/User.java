package com.onlineLearningPlatform.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String name;

    private String emailAddress;

    private String password;

    private boolean blocked;

    private boolean active = true;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER) // EAGER fetch is common for roles
    @CollectionTable(name = "user_roles", // Name of the join table for roles
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id")) // Foreign key in user_roles table
    @Column(name = "role_name", nullable = false) // Column in user_roles table to store the enum value
    @Enumerated(EnumType.STRING)
    private List<UserRole> roles = new ArrayList<>();

}
