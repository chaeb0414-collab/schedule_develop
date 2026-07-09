package com.example.schedule_develop.service;

import com.example.schedule_develop.dto.CreateScheduleRequest;
import com.example.schedule_develop.dto.ScheduleResponse;
import com.example.schedule_develop.dto.UpdateScheduleRequest;
import com.example.schedule_develop.entity.*;
import com.example.schedule_develop.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleResponse create(CreateScheduleRequest request, Long loginUserId) {
        User user = userRepository.findById(loginUserId)
                .orElseThrow(()-> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                user
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
    public ScheduleResponse update(Long id, UpdateScheduleRequest request, Long loginUserId){
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("일정을 찾을 수 없습니다."));

        if(!schedule.getUser().getId().equals(loginUserId)){
            throw new IllegalArgumentException("본인이 작성한 일정만 수정할 수 있습니다.");
        }

        schedule.update(
                request.getTitle(),
                request.getContent()
        );
        return new ScheduleResponse(schedule);
    }
    public void delete(Long id, Long loginUserId){
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("일정을 찾을 수 없습니다."));
        if (!schedule.getUser().getId().equals(loginUserId)){
            throw new IllegalArgumentException("본인이 작성한 일정만 삭제할 수 있습니다.");
        }
        scheduleRepository.delete(schedule);
    }
}
