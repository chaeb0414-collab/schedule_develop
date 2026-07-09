package com.example.schedule_develop.service;

import com.example.schedule_develop.dto.*;
import com.example.schedule_develop.entity.User;
import com.example.schedule_develop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse create(CreateUserRequest request){
        User user = new User(
        request.getName(),
        request.getEmail()
        );
        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser);
    }
    public List<UserResponse> findAll(){
        List<User> users = userRepository.findAll();

        List<UserResponse> responses = new ArrayList<>();

        for(User user : users){
            responses.add(new UserResponse(user));
        }
        return responses;
    }
    public UserResponse findById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        return new UserResponse(user);
    }
    @Transactional
    public UserResponse update(Long id, UpdateUserRequest request){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        user.update(
                request.getName(),
                request.getEmail()
        );
        return new UserResponse(user);
    }
    public void deleteById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        userRepository.delete(user);
    }
}
