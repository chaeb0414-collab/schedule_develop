package com.example.schedule_develop.controller;

import com.example.schedule_develop.dto.*;
import com.example.schedule_develop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserResponse create(@RequestBody CreateUserRequest request){
        return userService.create(request);
    }
    @GetMapping
    public List<UserResponse> findAll(){
        return userService.findAll();
    }
    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id){
        return userService.findById(id);
    }
    @PutMapping("/{id}")
    public UserResponse update(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request){
        return userService.update(id, request);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> delete(
            @PathVariable Long id){
        userService.deleteById(id);

        return ResponseEntity.ok(
                Map.of("message","삭제되었습니다.")
                );
    }
}
