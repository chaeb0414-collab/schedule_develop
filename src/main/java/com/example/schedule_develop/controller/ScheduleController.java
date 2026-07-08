package com.example.schedule_develop.controller;

import com.example.schedule_develop.dto.CreateScheduleRequest;
import com.example.schedule_develop.dto.ScheduleResponse;
import com.example.schedule_develop.dto.UpdateScheduleRequest;
import com.example.schedule_develop.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ScheduleResponse create(@RequestBody CreateScheduleRequest request){
        return scheduleService.create(request);
    }
    @GetMapping
    public List<ScheduleResponse> findAll(){
        return scheduleService.findAll();
    }
    @GetMapping("/{id}")
    public ScheduleResponse findById(@PathVariable Long id){
        return scheduleService.findById(id);
    }
    @PutMapping("/{id}")
    public ScheduleResponse update(
            @PathVariable Long id,
            @RequestBody UpdateScheduleRequest request
    ){
        return scheduleService.update(id, request);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        scheduleService.delete(id);

        return ResponseEntity.ok("삭제되었습니다.");
    }

}
