package com.example.schedule_develop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String title;

    private String content;

    public Schedule(String name, String title, String content) {
        this.name = name;
        this.title = title;
        this.content = content;
    }
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
