package com.example.schedule_develop.controller;

import com.example.schedule_develop.dto.*;
import com.example.schedule_develop.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ScheduleResponse create(
            @RequestBody CreateScheduleRequest request,
            HttpSession session
    ){
        getLoginUserId(session);
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
            @RequestBody UpdateScheduleRequest request,
            HttpSession session
    ){
        getLoginUserId(session);
        return scheduleService.update(id, request);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> delete(
            @PathVariable Long id,
            HttpSession session
            ){
        getLoginUserId(session);
        scheduleService.delete(id);

        return ResponseEntity.ok(
                Map.of("message","삭제되었습니다.")
        );
    }
    private Long getLoginUserId(HttpSession session){
        Long loginUserId = (Long) session.getAttribute("LOGIN_USER");

        if(loginUserId == null){
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        return loginUserId;
    }

}
