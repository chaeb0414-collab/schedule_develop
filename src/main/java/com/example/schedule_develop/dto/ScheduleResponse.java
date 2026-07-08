package com.example.schedule_develop.dto;

import com.example.schedule_develop.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponse {
    private Long id;
    private String name;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ScheduleResponse(Schedule schedule) {
        this.id = schedule.getId();
        this.name = schedule.getName();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }

}
