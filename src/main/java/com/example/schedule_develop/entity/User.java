package com.example.schedule_develop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)
    private String email;
    private String password;

    public User(String name, String email,  String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public void update(String name, String emails, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
