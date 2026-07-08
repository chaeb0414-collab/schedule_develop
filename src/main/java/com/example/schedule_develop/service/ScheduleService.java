package com.example.schedule_develop.service;

import com.example.schedule_develop.dto.CreateScheduleRequest;
import com.example.schedule_develop.dto.ScheduleResponse;
import com.example.schedule_develop.dto.UpdateScheduleRequest;
import com.example.schedule_develop.entity.Schedule;
import com.example.schedule_develop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponse create(CreateScheduleRequest request){
        Schedule schedule = new Schedule(
                request.getName(),
                request.getTitle(),
                request.getContent()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponse(savedSchedule);
    }
    public List<ScheduleResponse> findAll(){
        List<Schedule> schedules = scheduleRepository.findAll();

        List<ScheduleResponse> responses = new ArrayList<>();

        for (Schedule schedule : schedules) {
            responses.add(new ScheduleResponse(schedule));
        }
        return responses;
    }
    public ScheduleResponse findById(Long id){
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));

        return new ScheduleResponse(schedule);
    }
    @Transactional
    public ScheduleResponse update(Long id, UpdateScheduleRequest request){
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("일정을 찾을 수 없습니다."));

        schedule.update(
                request.getTitle(),
                request.getContent()
        );
        return new ScheduleResponse(schedule);
    }
    public void delete(Long id){
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("일정을 찾을 수 없습니다."));
        scheduleRepository.delete(schedule);
    }
}
